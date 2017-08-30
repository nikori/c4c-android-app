package mhealth.c4c;

/**
 * Created by kennedy on 6/15/17.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {
    Context context;

    String[] Options;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext,String[] options) {
        this.context = applicationContext;

        this.Options = options;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return Options.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_row, null);

        TextView names = (TextView) view.findViewById(R.id.spinnerText);
        names.setText(Options[i]);
        String mys=Options[i];
        if(mys.contentEquals("Please Select Gender")||mys.contentEquals("Please Select Marital Status")||mys.contentEquals("Please Select Condition")||mys.contentEquals("Please Select Language")||mys.contentEquals("Please Select Status")||mys.contentEquals("Please Select Cadre")||mys.contentEquals("Enable Sms")||mys.contentEquals("Where did the exposure occur?")||mys.contentEquals("What caused the exposure?")||mys.contentEquals("Have you been vaccinated against Hepatitis B?")){


            names.setBackgroundResource(R.color.mycolor);
            names.setTextSize(19);

//            names.setTextColor(getResources().getColor(R.color.colorPrimary));
        }


        return view;
    }
}
