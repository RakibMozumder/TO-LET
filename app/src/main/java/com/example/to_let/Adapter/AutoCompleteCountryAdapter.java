        package com.example.to_let.Adapter;

        import android.content.Context;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Filter;
        import android.widget.TextView;

        import com.example.to_let.R;
        import com.example.to_let.Model.UserInformation;

        import java.util.ArrayList;
        import java.util.List;


public class AutoCompleteCountryAdapter extends ArrayAdapter<UserInformation> {
    private Context context;
    private List<UserInformation> informationListFull;
    public AutoCompleteCountryAdapter(Context context, List<UserInformation> informationList) {
        super(context, 0, informationList);
        this.context=context;
        informationListFull = new ArrayList<>(informationList);

    }

    @Override
    public Filter getFilter() {
        return countryFilter;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.sample_search_row, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.locationHint);
       // ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);

        UserInformation userInformation = getItem(position);

        if (userInformation != null) {
            textViewName.setText(userInformation.getUserFirstName());

        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<UserInformation> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(informationListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (UserInformation item : informationListFull) {
                    if (item.getUserFirstName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((UserInformation) resultValue).getUserFirstName();
        }
    };
}