package shreyas.storelocator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import shreyas.storelocator.R;
import shreyas.storelocator.listener.StoreOnClickListener;
import shreyas.storelocator.model.StoreAddress;

/**
 * Created by shreyasmp on 10/24/17.
 */

public class StoreResultsAdapter extends RecyclerView.Adapter<StoreResultsAdapter.ViewHolder> {

    private Context context;
    private StoreOnClickListener storeOnClickListener;
    private ArrayList<StoreAddress> storeAddresses;

    public StoreResultsAdapter(Context context, StoreOnClickListener storeOnClickListener) {
        this.context = context;
        this.storeOnClickListener = storeOnClickListener;
        this.storeAddresses = new ArrayList<>();
    }

    @Override
    public StoreResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.store_list_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreResultsAdapter.ViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOnClickListener.onItemClick(storeAddresses.get(position));
            }
        });
        Picasso.with(context).load(storeAddresses.get(position).getStoreLogoURL()).into(holder.storeLogo);
        holder.storePhone.setText(storeAddresses.get(position).getPhone());

        /*String completeAddress = storeAddresses.get(position).getAddress() + "\n"
                + storeAddresses.get(position).getCity()+" "+ storeAddresses.get(position).getState()+" "+ storeAddresses.get(position).getZipcode();*/
        holder.storeAddress.setText(storeAddresses.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return storeAddresses == null ? 0 : storeAddresses.size();
    }

    public void setStoreDetailsArrayListData(ArrayList<StoreAddress> storeDetailsArrayListData) {
        this.storeAddresses = storeDetailsArrayListData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView storeLogo;
        public TextView storePhone;
        public TextView storeAddress;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.store_details_view);
            storeLogo = (ImageView) itemView.findViewById(R.id.store_logo);
            storePhone = (TextView) itemView.findViewById(R.id.store_contact_number);
            storeAddress = (TextView) itemView.findViewById(R.id.store_address);
        }
    }
}
