package com.example.to_let.Adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.to_let.R;
import com.example.to_let.Model.SearchModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<SearchModel>{
    private Context context;
    private List<SearchModel> locationListFull;
    public  String r = null;

    public SearchAdapter(Context context,List<SearchModel> locationList) {
        super(context,0,locationList);
        this.context=context;
        locationListFull=new ArrayList<>(locationList);
    }



    @Override
    public Filter getFilter()
    {
        return locationFilter;
    }


    @Override
    public View getView(int position, @Nullable  View convertView,   @Nullable ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sample_search_row, parent,false);

        }
        TextView textView=convertView.findViewById(R.id.locationHint);
        TextView textView1=convertView.findViewById(R.id.addressHint);
        SearchModel searchModel=  getItem(position);
        if(searchModel!=null){
                textView.setText(searchModel.getLocationHint());
                textView1.setText(searchModel.getAddressHint());
            }


        return convertView;
    }

    private Filter locationFilter=new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
          FilterResults results=new FilterResults();
          List<SearchModel>suggestionList =new ArrayList<>();
          if(constraint==null|| constraint.length()==0){
                suggestionList.addAll(locationListFull);
            }else{
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(SearchModel item:locationListFull){
                    if(item.getLocationHint().toLowerCase().contains(filterPattern)){
                        suggestionList.add(item);
                    }
                }

            }
            results.values=suggestionList;
            results.count=suggestionList.size();
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
            String res =((SearchModel)resultValue).getLocationHint()+","+((SearchModel)resultValue).getAddressHint();
            String r=res;
            return res;
        }
    };
}
