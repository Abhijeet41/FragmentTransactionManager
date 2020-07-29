package com.abhi41.fragmenttransactionmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi41.fragmenttransactionmanager.fragments.FragmentA;
import com.abhi41.fragmenttransactionmanager.fragments.FragmentB;
import com.abhi41.fragmenttransactionmanager.fragments.FragmentC;
import com.abhi41.fragmenttransactionmanager.fragments.FragmentD;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        message = findViewById(R.id.message);
        fragmentManager.addOnBackStackChangedListener(this);

    }

    public void replacewithA(View view) {

        FragmentB fragmentB  = new FragmentB();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.group,fragmentB,"replace with B");
        fragmentTransaction.addToBackStack("replace A with B");
        fragmentTransaction.commit();

    }

    public void replacewithB(View view) {
       /* FragmentA fragmentA  = new FragmentA();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.group,fragmentA,"replace with A");
        fragmentTransaction.addToBackStack("replace B with A");
        fragmentTransaction.commit();*/

        fragmentManager.popBackStack();
    }
    public void replacewithC(View view) {
        FragmentC fragmentC  = new FragmentC();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.group,fragmentC,"replace with C");
        fragmentTransaction.addToBackStack("replace B with C");
        fragmentTransaction.commit();
        //fragmentManager.popBackStack();
    }

    public void addA(View view) {

        FragmentA fragmentA = new FragmentA();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.group,fragmentA,"A");
       // transaction.addToBackStack("addA");
        transaction.commit();
    }
    public void removeA(View view) {
        FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("A");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentA != null)
        {
            transaction.remove(fragmentA);
            transaction.addToBackStack("removeA");
            transaction.commit();
        }else {
            Toast.makeText(this, "Fragment A was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void detachA(View view) {
        FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("A");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (fragmentA != null)
        {
            fragmentTransaction.detach(fragmentA);
            fragmentTransaction.addToBackStack("detachA");
            fragmentTransaction.commit();
        }

    }

    public void addB(View view) {
        FragmentB fragmentB = new FragmentB();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.group,fragmentB,"B");
        transaction.addToBackStack("addB");
        transaction.commit();
    }

    public void removeB(View view) {
        FragmentB fragmentB = (FragmentB) fragmentManager.findFragmentByTag("B");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentB != null)
        {
            transaction.remove(fragmentB);
            transaction.addToBackStack("removeB");
            transaction.commit();
        }else {
            Toast.makeText(this, "Fragment B was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachA(View view) {

         FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("A");
         FragmentTransaction transaction = fragmentManager.beginTransaction();
         if (fragmentA != null)
         {
             transaction.attach(fragmentA);
             transaction.addToBackStack("attachA");
             transaction.commit();
         }
    }

    public void replacewithD(View view) {

        FragmentD fragmentD  = new FragmentD();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.group,fragmentD,"replace with D");
        fragmentTransaction.addToBackStack("replace C with D");
        fragmentTransaction.commit();
    }

    public void Back(View view) {
        fragmentManager.popBackStack();
    }


    public void popAddB(View view) {
        fragmentManager.popBackStack("addB",FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackStackChanged() {
        message.setText(message.getText()+"\n");
        message.setText(message.getText()+"The current status of Back Stack");

        int count = fragmentManager.getBackStackEntryCount();
        for (int i = count-1; i>=0; i--)
        {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            message.setText(message.getText()+" "+entry.getName()+" \n");
        }
        message.setText(message.getText()+" \n");

    }

    public void popAddC(View view) {
        FragmentC fragmentC = new FragmentC();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.group,fragmentC,"C");
        transaction.addToBackStack("addC");
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

        int count = fragmentManager.getBackStackEntryCount();
        if (count == 0)
        {
            Toast.makeText(this, "backstack is 0", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }else {
            fragmentManager.popBackStack();
        }

    }



}