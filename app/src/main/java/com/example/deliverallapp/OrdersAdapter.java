package com.example.deliverallapp;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deliverallapp.Order;
import com.example.deliverallapp.R;

import java.util.List;

public class OrdersAdapter extends BaseAdapter {
    private List<Order> orders;
    private LayoutInflater inflater;
    private int resource;

    public OrdersAdapter(Context context, int resource, List<Order> orders) {
        this.orders = orders;
        this.resource = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_list_button, parent, false);
        }

        Order order = getItem(position);

        TextView firmTextView = convertView.findViewById(R.id.textviewFirm);
        TextView typeTextView = convertView.findViewById(R.id.textviewType);
        TextView fromTextView = convertView.findViewById(R.id.textviewFrom);
        TextView toTextView = convertView.findViewById(R.id.textviewTo);

        firmTextView.setText(order.getFirm().getName());
        typeTextView.setText(order.getaPackage().getType());
        fromTextView.setText(order.getAddressFrom());
        toTextView.setText(order.getAddressTo());

        return convertView;
    }
}
