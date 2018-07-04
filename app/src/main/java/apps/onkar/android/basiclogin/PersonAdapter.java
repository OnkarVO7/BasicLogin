package apps.onkar.android.basiclogin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_person, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView numberTextView = (TextView) convertView.findViewById(R.id.numberTextView);
        TextView emailTextView = (TextView) convertView.findViewById(R.id.emailTextView);

        Person person = getItem(position);

        nameTextView.setText(person.getName());
        numberTextView.setText(person.getMobileno());
        emailTextView.setText(person.getEmail());

        return convertView;
    }
}
