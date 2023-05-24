package com.example.deliverallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



// (сделано)попытаться избавиться от allcouriers и засунуть все в коллекцию которая будет в мэйнактивити
// (сделано)сделать чтобы все было в одном листвью, попытаться сделать в ресайклервью мб
// (сделано) посмотреть тройной цикл в удалении заказа чтобы её не было
// !!!регистрация через гугл через апи(возможно недоступно)
// (сделано)убрать дофига обращений в мэйнактивити и все эти статические переменные закинуть в отдельный класс
public class MainActivity extends AppCompatActivity {
    Button btnSignIn, btnRegister;
    RelativeLayout root;
    String pathFile = "courrrrrrrr.bin";
    private Firm firm1;
    private Firm firm2;
    private Firm firm3;
    private Firm firm4;
    private Firm firm5;
    private Firm firm6;
    private PackageBig packageBig1;
    private PackageBig packageBig2;
    private PackageBig packageBig3;
    private PackageSmall packageSmall1;
    private PackageSmall packageSmall2;
    private PackageSmall packageSmall3;

    private PackageDocs packageDocs1;
    private PackageDocs packageDocs2;

    private PackageDocs packageDocs3;

    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;
    private Order order5;
    private Order order6;
    private Order order7;
    private Order order8;
    private Order order9;
    private Order order10;
    private Order order11;
    private Order order12;
    private Order order13;
    private Order order14;

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
        finishAffinity();
        super.onBackPressed();
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
    private void receiveInfo() throws IOException, ClassNotFoundException {
        File file = new File(getFilesDir(), pathFile);
        if(file.length() == 0){
            return;
        }
        FileInputStream fis = openFileInput(pathFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        int numOfCouriers = ois.readInt();
        for (int i = 0; i < numOfCouriers; ++i){
            Courier newCur = (Courier)ois.readObject();
            Courier newCourier = new Courier(newCur.getName(), newCur.getSurname(),
                    newCur.getPaymentAccount(), newCur.isHasCar(), newCur.isCanDeliverDocs(),
                    newCur.getPassword(), newCur.getPhone(), newCur.getEmail(), newCur.getOrders());
        }
        ois.close();
        fis.close();



    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.buttonSignIn);
        btnRegister = findViewById(R.id.buttonRegister);
        root = findViewById(R.id.root_element);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showRegisterWindow();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showSignInWindow();
            }
        });
        try {
            receiveInfo();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (OurSessionData.getAvailableOrders().size() == 0){
            firm1 = new Firm("Megaphone", "Kosukhina 8");
            firm2 = new Firm("VTB", "Lenina 3");
            firm3 = new Firm("Avangard", "Studencheskaya 1");
            firm4 = new Firm("Metall Pro", "Pirogova 15");
            firm5 = new Firm("Invitro","Komarova 8");
            firm6 = new Firm("Gazprom", "Mukhina 125");

            packageBig1 = new PackageBig(true, 80);
            packageBig2 = new PackageBig(false, 30);
            packageBig3 = new PackageBig(true, 120);

            packageSmall1 = new PackageSmall(true, false);
            packageSmall2 = new PackageSmall(false, false);
            packageSmall3 = new PackageSmall(false, false);

            packageDocs1 = new PackageDocs("small", false, false,
                    "Petrovich", "Ivanych");
            packageDocs2 = new PackageDocs("big", false, true,
                    "Mo", "Po");
            packageDocs3 = new PackageDocs("small", false, false,
                    "On", "Ona");

            order1 = new Order(1,firm1, packageBig1, "Karla Marksa 2",
                    400);
            order2 = new Order(2, firm2, packageSmall1, "M1",
                    200);
            order3 = new Order(3, firm3, packageDocs1, "Seryogina 20",
                    250);
            order4 = new Order(4, firm4, packageBig2, "Stepanova 22",
                    500);
            order5 = new Order(5, firm5, packageBig3, "Lenina 50",
                    400);
            order6 = new Order(6, firm6, packageSmall2, "Dobrova 11",
                    300);
            order7 = new Order(7, firm3, packageSmall3, "Semyonovskaya 42",
                    250);
            order8 = new Order(8, firm4, packageDocs2, "Baumanskaya 7",
                    600);
            order9 = new Order(9,firm1, packageDocs3, "Serova 31",
                    300);
            order10 = new Order(10, firm4, packageBig3, "Lenina 10",
                    500);
            order11 = new Order(11, firm5, packageSmall2, "Korzhova 1",
                    100);
            order12 = new Order(12, firm1, packageSmall3, "Maslova 42",
                    250);
            order13 = new Order(13, firm4, packageDocs2, "50 let Oktyabrya 7",
                    600);
            order14 = new Order(14, firm5, packageDocs3, "Burovskaya 62",
                    300);
        }





    }
    public boolean inCorrectData(EditText pass, EditText email){
        if (TextUtils.isEmpty(pass.getText().toString()) || pass.getText().toString().
                length() < 8){
            return true;
        }
        if (TextUtils.isEmpty(email.getText().toString())){
            return true;
        }

        boolean has_email = false;
        for (int i = 0; i < OurSessionData.getCourierList().size(); ++i){
            if (email.getText().toString().equals(OurSessionData.getCourierList().get(i).getEmail())){
                has_email = true;
                OurSessionData.setCurCourier(OurSessionData.getCourierList().get(i));
                if (!pass.getText().toString().equals(OurSessionData.getCourierList().get(i).
                        getPassword())){
                    return true;
                }
            }
        }
        if(!has_email){
            return true;
        }
        return false;
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Вход");
        dialog.setMessage("Введите пароль и эл. почту вашего аккаунта");
        LayoutInflater inflater = LayoutInflater.from(this);
        View rg_window = inflater.inflate(R.layout.entry_window, null);
        dialog.setView(rg_window);

        EditText email = rg_window.findViewById(R.id.email_field);
        EditText pass = rg_window.findViewById(R.id.pass_field);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (inCorrectData(pass, email)){
                    Snackbar.make(root, "Пароль или эл. почта введены неверно",
                            Snackbar.LENGTH_SHORT).show();
                    return;

                }
                startActivity(new Intent(MainActivity.this, MainPageActivity.class));

            }
        });
        dialog.show();
    }

    private boolean badPassword(EditText pass){
        if (TextUtils.isEmpty(pass.getText().toString()) || pass.getText().toString().
                length() < 8){
            Snackbar.make(root, "Введите пароль длиной не менее 8 символов",
                    Snackbar.LENGTH_SHORT).show();
            return true;
        }
        for (int i = 0; i < OurSessionData.getCourierList().size(); ++i){
            if (pass.getText().toString().equals(OurSessionData.getCourierList().get(i).getPassword())){
                Snackbar.make(root, "Придумайте другой пароль",
                        Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
    private boolean badEmail(EditText email){
        if (TextUtils.isEmpty(email.getText().toString())){
            Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
            return true;

        }
        for (int i = 0; i < OurSessionData.getCourierList().size(); ++i){
            if (email.getText().toString().equals(OurSessionData.getCourierList().get(i).getEmail())){
                Snackbar.make(root, "Аккаунт с такой эл. почтой уже существует",
                        Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
    private boolean badPhone(EditText phone){
        if (TextUtils.isEmpty(phone.getText().toString())){
            Snackbar.make(root, "Введите ваш номер телефона", Snackbar.LENGTH_SHORT).show();
            return true;

        }
        for (int i = 0; i < OurSessionData.getCourierList().size(); ++i){
            if (phone.getText().toString().equals(OurSessionData.getCourierList().get(i).getPhone())){
                Snackbar.make(root, "Такой номер телефона уже зарегистрирован",
                        Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите данные для регистрации");
        LayoutInflater inflater = LayoutInflater.from(this);
        View rg_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(rg_window);

        CheckBox checkBox_car = rg_window.findViewById(R.id.check_car);
        CheckBox checkBox_docs = rg_window.findViewById(R.id.check_docs);
        EditText email = rg_window.findViewById(R.id.email_field);
        EditText pass = rg_window.findViewById(R.id.pass_field);
        EditText name = rg_window.findViewById(R.id.name_field);
        EditText surname = rg_window.findViewById(R.id.surname_field);
        EditText phone = rg_window.findViewById(R.id.phone_field);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (badEmail(email)){
                    return;

                }
                if (badPassword(pass)){
                    return;

                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(surname.getText().toString())){
                    Snackbar.make(root, "Введите вашу фамилию", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (badPhone(phone)){
                    return;

                }
                Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались!",
                        Toast.LENGTH_LONG) .show();
                Courier newCourier = new Courier(name.getText().toString(),
                        surname.getText().toString(),
                        (long)((Math.random() * (100000000 - 10000000 )) + 100000000), checkBox_car.isChecked(),
                        checkBox_docs.isChecked(), pass.getText().toString(), phone.getText().toString(),
                        email.getText().toString());


            }
        });
        dialog.show();
    }

}