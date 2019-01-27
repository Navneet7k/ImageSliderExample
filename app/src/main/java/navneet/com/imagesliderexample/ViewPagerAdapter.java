package navneet.com.imagesliderexample;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;



public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    private ArrayList<SlideItem> records;
    private int numberofRecords;
    LayoutInflater inflater;


    public ViewPagerAdapter(Context context, ArrayList<SlideItem> results,
                            int numberofRecords) {
        this.context = context;
        this.records = results;
        this.numberofRecords = numberofRecords;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((CardView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.card_row, container,
                false);

        ImageView slider=(ImageView) itemView.findViewById(R.id.slider);
        final TextView car_name=(TextView) itemView.findViewById(R.id.carname);

        final String carName=records.get(position).getCarName();
        final String carType=records.get(position).getCarType();

        car_name.setText(carName);

        if (carName.equals("benz")) {
            slider.setImageResource(R.drawable.benz);
        } else if(carName.equals("bmw")) {
            slider.setImageResource(R.drawable.bmw);
        } else if(carName.equals("bentley")) {
            slider.setImageResource(R.drawable.bentley);
        } else if(carName.equals("volvo")) {
            slider.setImageResource(R.drawable.volvo);
        } else {
            slider.setImageResource(R.drawable.ic_launcher_background);
        }

        ((ViewPager) container).addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((CardView) object);
    }

    @Override
    public int getCount() {
        return numberofRecords;
    }
}
