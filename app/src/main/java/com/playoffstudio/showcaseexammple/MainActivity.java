package com.playoffstudio.showcaseexammple;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public ShowcaseView ShowCase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //ViewTarget target = new ViewTarget(toolbar.findViewById(R.id.menu_id_launcher));
        //ViewTarget target1 = new ViewTarget(toolbar.findViewById(R.id.menu_id_launcher));

        /*
        Target homeTarget = new Target() {
            @Override
            public Point getPoint() {
                // Get approximate position of home icon's center
                int actionBarSize = toolbar.getHeight();
                int x = actionBarSize*10;
                int y = actionBarSize/5;
                return new Point(x, y);
            }
        };



        Target target = new Target() {
            @Override
            public Point getPoint() {
                int[] location = new int[2];
                toolbar.getLocationInWindow(location);
                int x = location[0] + toolbar.getWidth() - toolbar.getHeight() / 100;
                int y = location[1] + toolbar.getHeight() / 100;
                return new Point(x, y);
            }
        };



        new ShowcaseView.Builder(this)
                .setContentTitle("Its My Navigation Drawer")
                .setContentText("Click here and you will get options to navigate to other sections.")
                .useDecorViewAsParent() //this is the difference
                .setTarget(target)
                .build();

        */
        showcaseDialogTutorial();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void showcaseDialogTutorial(){
        final SharedPreferences tutorialShowcases = getSharedPreferences("showcaseTutorial", MODE_PRIVATE);

        boolean run;

        run = tutorialShowcases.getBoolean("run?", true);

        if(run){//If the buyer already went through the showcases it won't do it again.
            final ViewTarget target1  = new ViewTarget(R.id.menu_id_launcher , this);//Variable holds the item that the showcase will focus on.
            final ViewTarget target2 = new ViewTarget(R.id.action_settings , this);
            final ViewTarget target3 = new ViewTarget(R.id.fab , this);



            //This code creates a new layout parameter so the button in the showcase can move to a new spot.
            final RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // This aligns button to the bottom left side of screen
            lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lps.addRule(RelativeLayout.CENTER_HORIZONTAL);
            // Set margins to the button, we add 16dp margins here
            int margin = ((Number) (getResources().getDisplayMetrics().density * 16)).intValue();
            lps.setMargins(margin, margin, margin, margin);


            //This creates the first showcase.
            ShowCase = new ShowcaseView.Builder(this)
                    //.withMaterialShowcase()
                    .setTarget(target1)
                    .setContentTitle("Target 1 ")

                    .setContentText("Target 1 ")
                    .useDecorViewAsParent() //this is the difference
                    .build();
            ShowCase.setButtonText("next");

            //When the button is clicked then the switch statement will check the counter and make the new showcase.
            ShowCase.overrideButtonClick(new View.OnClickListener() {
                int count1 = 0;

                @Override
                public void onClick(View v) {
                    count1++;
                    switch (count1) {
                        case 1:
                            ShowCase.setTarget(target2);
                            ShowCase.setContentTitle("");
                            ShowCase.setContentText("");
                            //ShowCase.setButtonPosition(L);
                            ShowCase.setButtonText("next");
                            //ShowCase.hide();
                            break;

                        case 2:
                            ShowCase.setTarget(target3);

                            ShowCase.setContentTitle("");
                            ShowCase.setContentText("");
                            ShowCase.setButtonText("finish");
                            //ShowCase.hide();
                            break;

/*
                        case 11:
                            SharedPreferences.Editor tutorialShowcasesEdit = tutorialShowcases.edit();
                            tutorialShowcasesEdit.putBoolean("run?", false);
                            tutorialShowcasesEdit.apply();
                            break;
                            */
                    }
                }
            });
        }
    }
}
