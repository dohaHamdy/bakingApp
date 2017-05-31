package com.example.dohahamdy.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by DOHA HAMDY on 5/31/2017.
 */

public class WidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


        //Toast.makeText(context, title1,Toast.LENGTH_LONG).show()
        return new WidgetDataProvider(this, intent);
    }
    @Override
    public void onStart(Intent intent, int startId) {
        this.onGetViewFactory(intent);
        //  WidgetDataProvider widgetDataProvider=new WidgetDataProvider(this, intent);
    }


}
