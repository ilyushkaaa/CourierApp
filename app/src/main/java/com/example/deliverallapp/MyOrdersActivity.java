package com.example.deliverallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyOrdersActivity extends AppCompatActivity {

    private OrdersOfCourierAdapter arrayAdapter;
    private ListView lwOrders;
    private String pathFile = "courrrrrrrr.bin";
    @Override
    protected void onPause(){
        super.onPause();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void saveInfo() throws IOException {
        FileOutputStream fos = openFileOutput(pathFile, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(OurSessionData.getCourierList().size());
        for (Courier cou: OurSessionData.getCourierList()){
            oos.writeObject(cou);
        }
        oos.close();
        fos.close();
    }
    public void showSum(View view){
        int sum = 0;
        for (int i = 0; i < OurSessionData.getCurCourier().getOrders().size(); ++i){
            sum += OurSessionData.getCurCourier().getOrders().get(i).getPrice();
        }
        String sumStr = Integer.toString(sum);
        String sumOrder = "Общая стоимость вашего заказа: " + sumStr + " рублей";

        Toast.makeText(MyOrdersActivity.this, sumOrder, Toast.LENGTH_LONG).show();
    }


    private void showOrders(){
        lwOrders = findViewById(R.id.OrdersCour);
        arrayAdapter = new OrdersOfCourierAdapter(this,
                R.layout.my_list_button_my_order, OurSessionData.getCurCourier().getOrders());
        lwOrders.setAdapter(arrayAdapter);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        showOrders();
        TextView textView = findViewById(R.id.infoCourier);
        String has_car = "     has car";
        String can_docs = "     can deliver docs";
        if (!OurSessionData.getCurCourier().isHasCar()){
            has_car = "      no car";
        }
        if (!OurSessionData.getCurCourier().isCanDeliverDocs()){
            can_docs = "     can't deliver docs";
        }

        String courierInfo = OurSessionData.getCurCourier().getName() + " "+
                OurSessionData.getCurCourier().getSurname() + "    Payment account: " +
                Long.toString(OurSessionData.getCurCourier().getPaymentAccount()) + has_car +
                can_docs + "    Phone number: " + OurSessionData.getCurCourier().getPhone();
        textView.setText(courierInfo);
    }
    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goChoose(View view){
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }
    public void onRemoveClick(View view){
        Toast.makeText(MyOrdersActivity.this, "Заказ удалён", Toast.LENGTH_LONG).show();
        int pos = lwOrders.getPositionForView(view);
        OurSessionData.getCurCourier().deleteOrder(OurSessionData.getCurCourier().getOrders().get(pos));
        arrayAdapter.notifyDataSetChanged();
        showOrders();
    }
}