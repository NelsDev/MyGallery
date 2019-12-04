package fr.can.mygallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.can.mygallery.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.can.mygallery.retrofit.api.model.User;

public class UserAdapter extends ArrayAdapter<User> {

protected class Holder {

    @BindView(R.id.item_name)
    protected TextView name;

    @BindView(R.id.item_username)
    protected TextView username;

    @BindView(R.id.item_mail)
    protected TextView mail;

    @BindView(R.id.item_phone)
    protected TextView phone;

    @BindView(R.id.item_website)
    protected TextView website;

    Holder(@NonNull View view) {
        ButterKnife.bind(this, view);
    }

    private void updateUI(String name, String username, String mail, String phone, String website) {
        this.name.setText(name);
        this.username.setText(username);
        this.mail.setText(mail);
        this.phone.setText(phone);
        this.website.setText(website);
    }
}

    public UserAdapter(Context context, List<User> userInformation) {
        super(context, R.layout.item_user, userInformation);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.item_user, parent, false);

        if (convertView != null) {
            Holder holder = new Holder(convertView);
            User user = getItem(position);
            holder.updateUI(
                    user.getName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getWebsite());
        }

        return convertView;
    }
}
