package com.appministrator.synnectt.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appministrator.synnectt.R;
import com.appministrator.synnectt.models.event;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.DateFormat;

public class eventsRvAdapter extends FirestoreRecyclerAdapter<event, eventsRvAdapter.eventRvHolder> {

    private static final String TAG = "eventsRvAdapter";
    public eventsRvAdapter(@NonNull FirestoreRecyclerOptions<event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull eventRvHolder holder, int position, @NonNull event model) {
        holder.eventTitle.setText(model.getName());
        holder.eventDate.setText(DateFormat.getDateInstance().format(model.getDate().toDate()));
        holder.eventLoc.setText(model.getVenue());
    }

    @NonNull
    @Override
    public eventRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_event_card, parent, false);
        Log.d(TAG, "onCreateViewHolder: Item added");
        return new eventRvHolder(v);
    }

    class eventRvHolder extends RecyclerView.ViewHolder {

        TextView eventTitle, eventLoc, eventDate;
        public eventRvHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.eventTitleTv);
            eventLoc = itemView.findViewById(R.id.eventLocationTv);
            eventDate = itemView.findViewById(R.id.eventDateTv);
        }
    }
}
