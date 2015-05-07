package in.zerob13.torrenthunter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zerob13 on 5/7/15.
 */
public class ResultListAdapter implements ListAdapter, View.OnLongClickListener {
    private Context mContext;
    private ArrayList<String> mNameList;
    private ArrayList<String> mLinkList;

    public ResultListAdapter(Context context, ArrayList<String> name, ArrayList<String> link) {
        mNameList = new ArrayList<String>();
        mLinkList = new ArrayList<String>();
        mNameList.addAll(name);
        mLinkList.addAll(link);
        mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mNameList.size() > mLinkList.size() ? mLinkList.size() : mNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater iff = LayoutInflater.from(mContext);
        View item = iff.inflate(R.layout.search_res_item, null);
        TextView name = (TextView) item.findViewById(R.id.item_name);
        TextView link = (TextView) item.findViewById(R.id.item_link);
        name.setText(mNameList.get(position));
        link.setText(mLinkList.get(position));
        link.setLongClickable(true);
        link.setOnLongClickListener(this);
        return item;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v instanceof TextView) {
            TextView links = (TextView) v;

            ClipboardManager cp = (ClipboardManager)
                    mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("magnet-link", links.getText());
            cp.setPrimaryClip(clip);
            Toast toast = Toast.makeText(mContext, R.string.link_copied, Toast.LENGTH_LONG);
            toast.show();
        }
        return false;
    }
}
