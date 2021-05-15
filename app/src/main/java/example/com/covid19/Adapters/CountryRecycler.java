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

import example.com.covid19.DataBases.Country.CountryModel;
import example.com.covid19.R;

public class CountryRecycler extends ListAdapter<CountryModel, CountryRecycler.CountryHolder> {
    private static final String TAG = "Team Fixture Adapter";

    private Context context;
    public CountryRecycler() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CountryModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<CountryModel>() {
        @Override
        public boolean areItemsTheSame(CountryModel oldItem, CountryModel newItem) {
            return oldItem.getCountry().equals(newItem.getCountry());
        }

        @Override
        public boolean areContentsTheSame(CountryModel oldItem, CountryModel newItem) {
            return true;
        }
    };

    @NonNull
    @Override
    public CountryRecycler.CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_raw, parent, false);
        return new CountryRecycler.CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecycler.CountryHolder holder, int position) {
        //Fixture currentTeam = getItem(position);
        CountryModel currentCountry = getItem(position);
        Log.d(TAG, "onBindViewHolder: " + currentCountry.getCountry());
        holder.country.setText(currentCountry.getCountry());
    }

    public CountryModel getTeamAt(int position) {
        return getItem(position);
    }

    class CountryHolder extends RecyclerView.ViewHolder {

        private TextView country;

        public CountryHolder(View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.countryName);

        }
    }


}