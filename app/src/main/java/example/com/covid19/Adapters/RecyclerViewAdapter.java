package example.com.covid19.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.R;

public class RecyclerViewAdapter extends ListAdapter<HistoryModel, RecyclerViewAdapter.HistoryHolder> {
    private static final String TAG = "Team Fixture Adapter";

    private Context context;
    public RecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<HistoryModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<HistoryModel>() {
        @Override
        public boolean areItemsTheSame(HistoryModel oldItem, HistoryModel newItem) {
            return oldItem.getTime().equals(newItem.getTime()) && oldItem.getDay().equals(newItem.getDay()); /*oldItem.getId() == newItem.getId();*/
        }

        @Override
        public boolean areContentsTheSame(HistoryModel oldItem, HistoryModel newItem) {
            return true;
        }
    };

    @NonNull
    @Override
    public RecyclerViewAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_raw, parent, false);
        return new RecyclerViewAdapter.HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.HistoryHolder holder, int position) {
        //Fixture currentTeam = getItem(position);
        String placeholder = holder.itemView.getContext().getResources().getString(R.string.noInfo);
        HistoryModel currentCountry = getItem(position);
        Log.d(TAG, "onBindViewHolder: " + currentCountry.getCountry());
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.country.setText(currentCountry.getCountry().toString());
        Log.d(TAG, "onBindViewHolder: " + currentCountry.getTime() +" " + currentCountry.getDay() + " " + currentCountry.getId());
        if(currentCountry.getCases() != null){
            holder.casesCount.setText(currentCountry.getCases().toString());
        }
        else{

            holder.casesCount.setText(placeholder);
        }
        if(currentCountry.getDeaths() != null){
            holder.deathsCount.setText(currentCountry.getDeaths());
        }
        else{
            holder.deathsCount.setText(placeholder);
        }
        holder.day.setText(currentCountry.getDay());
        String[] data = currentCountry.getTime().toString().split("T");

        holder.time.setText(data[1]);
        holder.populationCount.setText(currentCountry.getPopulation());
    }

    public HistoryModel getTeamAt(int position) {
        return getItem(position);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        private TextView country;
        private TextView deathsCount;
        private TextView casesCount;
        private TextView recoveredCount;
        private TextView populationCount;
        private TextView time;
        private TextView day;
        public HistoryHolder(View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.countryName);
            deathsCount = itemView.findViewById(R.id.newDeathsCount);
            casesCount = itemView.findViewById(R.id.newCasesCount);
            recoveredCount = itemView.findViewById(R.id.newRecoveredCount);
            populationCount = itemView.findViewById(R.id.populationCount);
            time = itemView.findViewById(R.id.currentTime);
            day = itemView.findViewById(R.id.currentDate);
        }
    }


}