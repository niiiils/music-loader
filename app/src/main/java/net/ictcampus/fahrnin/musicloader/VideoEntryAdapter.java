package net.ictcampus.fahrnin.musicloader;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VideoEntryAdapter extends ArrayAdapter<VideoEntry> implements View.OnClickListener{

    private ArrayList<VideoEntry> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDuration;
        TextView txtViews;
        ImageView imgThumbnail;
    }

    public VideoEntryAdapter(ArrayList<VideoEntry> data, Context context) {
        super(context, R.layout.video_list_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VideoEntry entry = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.video_list_item, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.video_title);
            viewHolder.txtDuration = (TextView) convertView.findViewById(R.id.video_duration);
            viewHolder.txtViews = (TextView) convertView.findViewById(R.id.video_views);
            viewHolder.imgThumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtTitle.setText(entry.getTitle());
        //viewHolder.txtDuration.setText(entry.getDuration());
        //viewHolder.txtViews.setText(entry.getViews());
        //viewHolder.imgThumbnail.setImageResource(entry.getImage());
        // Return the completed view to render on screen
        return convertView;
    }
}