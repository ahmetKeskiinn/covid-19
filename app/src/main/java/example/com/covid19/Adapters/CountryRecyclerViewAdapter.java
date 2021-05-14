package example.com.covid19.Adapters;

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

public class CountryRecyclerViewAdapter extends ListAdapter<CountryModel, CountryRecyclerViewAdapter.CountryHolder> {
    private static final String TAG = "Team Adapter";
    private OnItemClickListener listener;

    public CountryRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CountryModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<CountryModel>() {
        @Override
        public boolean areItemsTheSame(CountryModel oldItem, CountryModel newItem) {
            return oldItem.getCountry().equals(newItem.getCountry());
        }

        @Override
        public boolean areContentsTheSame(CountryModel oldItem, CountryModel newItem) {
            return oldItem.getCountry().equals(newItem.getCountry());
        }
    };

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_raw, parent, false);
        return new CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        CountryModel currentCountry = getItem(position);
        holder.textViewTitle.setText(currentCountry.getCountry());
    }

    public CountryModel getCountryAt(int position) {
        return getItem(position);
    }

    class CountryHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        public CountryHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.countryName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CountryModel country);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}