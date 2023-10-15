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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class eventsRvAdapter extends FirestoreRecyclerAdapter<event, eventsRvAdapter.eventRvHolder> {

    private static final String TAG = "eventsRvAdapter";
    public eventsRvAdapter(@NonNull FirestoreRecyclerOptions<event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull eventRvHolder holder, int position, @NonNull event model) {
        holder.eventTitle.setText(model.getName());
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
        try {
            holder.eventDate.setText(format2.format(Objects.requireNonNull(format1.parse(model.getDate()))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
