package haj.test.com.haj;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import haj.test.com.haj.Models.subject;

/**
 * Created by zahra on 08/10/2015.
 */
public class SubjectListAdapter extends BaseAdapter {

    private Context mcontext;
    private List<subject> subjectlist;
    public SubjectListAdapter(Context context,List<subject> subjectlist)
    {
        this.mcontext=context;
        this.subjectlist=subjectlist;
    }


    @Override
    public int getCount() {
        return subjectlist.size();
    }

    @Override
    public subject getItem(int position) {
        return subjectlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder {
        public TextView title;
        public LinearLayout parent;
        public ViewHolder() {
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        subject item = subjectlist.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //int layout = ((item.isHeader) ? R.layout.drawer_header : R.layout.drawer_item);
            //convertView = LayoutInflater.from(mcontext).inflate(layout, null);

            convertView = LayoutInflater.from(mcontext).inflate(R.layout.row, null);

            holder.title = (TextView) convertView.findViewById(R.id.txtName);
            holder.parent=(LinearLayout)convertView.findViewById(R.id.parent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(item.Name);
        holder.title.setTypeface(MainActivity.font);

        if(position%2==0)
        {
            holder.parent.setBackgroundColor(Color.parseColor("#c6e2ee"));
        }
        else
        {
            holder.parent.setBackgroundColor(Color.parseColor("#afcad6"));
        }
        return convertView;
    }
}
