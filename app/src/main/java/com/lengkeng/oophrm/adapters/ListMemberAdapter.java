package com.lengkeng.oophrm.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.Employee;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListMemberAdapter extends BaseAdapter {
    Context mContext;
    List<Employee> listEmployees = null;
    ArrayList<Employee> mArrayList;
    ArrayList<Employee> tempArr;
    LayoutInflater inflater;
    Integer id;

    public ListMemberAdapter(ArrayList<Employee> mArrayList, Context mContext) {
        this.mArrayList = mArrayList;
        this.tempArr = new ArrayList<>(mArrayList);
        this.listEmployees = new ArrayList<Employee>(mArrayList);
        this.mContext = mContext;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
        holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_member_list_item, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.group = (TextView) convertView.findViewById(R.id.group);;
            holder.position = (TextView) convertView.findViewById(R.id.position);;
            holder.sex = (ImageView) convertView.findViewById(R.id.imguser);
            holder.btndelete = (Button) convertView.findViewById(R.id.delete_employee);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        holder.name.setText(((Employee)getItem(position)).getName());
        holder.position.setText(((Employee)getItem(position)).getPosition());
        holder.group.setText(((Employee)getItem(position)).getGroup());
        if(((Employee)getItem(position)).getSex().equals("Nam"))
            holder.sex.setImageResource(R.drawable.user_boy);
        else holder.sex.setImageResource(R.drawable.user_girl);
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "id" + ((Employee) getItem(position)).getId(), Toast.LENGTH_LONG).show();
                id = ((Employee) getItem(position)).getId();

                new deleteitem().execute(id, position);
            }
        });
        return convertView;
    }

    class deleteitem extends AsyncTask<Integer, Void, Integer>{
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Loading. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            pDialog.cancel();
            notifyDataSetChanged();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://vinhthien.name.vn/api/request?func=delete_employee&id=" + params[0]);
            try {
                client.execute(httpGet);
                mArrayList.remove(getItem(params[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mArrayList.clear();

        if (charText.length()>0) {
            listEmployees.addAll(mArrayList);

            for (Employee e : listEmployees) {
                if (e.getName().toLowerCase().contains(charText.toLowerCase())) {
                    mArrayList.add(e);
                }
                if (e.getPosition().toLowerCase().contains(charText.toLowerCase())) {
                    mArrayList.add(e);
                }
                if (e.getGroup().toLowerCase().contains(charText.toLowerCase())) {
                    mArrayList.add(e);
                }
            }
        } else {
            mArrayList = tempArr;
        }
        notifyDataSetChanged();
    }


    static class ViewHolder {
        TextView name;
        TextView group;
        TextView position;
        ImageView sex;
        Button btndelete;
        TextView dateOfBirth;
    }

    private class EmployeeFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>=0) {
                ArrayList<Employee> tempList = new ArrayList<Employee>();

                // search content in friend list
                for (Employee e : mArrayList) {
                    if (e.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(e);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = mArrayList.size();
                filterResults.values = mArrayList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}
