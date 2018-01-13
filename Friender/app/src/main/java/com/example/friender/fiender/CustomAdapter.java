package com.example.friender.fiender;

         import android.content.Context;
         import android.view.LayoutInflater;
         import android.view.View;
         import android.view.View.OnClickListener;
         import android.view.ViewGroup;
         import android.widget.BaseAdapter;
         import android.widget.ImageView;
         import android.widget.TextView;
         import android.widget.Toast;
public class CustomAdapter extends BaseAdapter{
    String [] result;
    String [] oceny;
    String [] wieki;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(FriendsDiary mainActivity, String[] nameList, int[] images, String[] ocenaList, String[] wiekList) {
        // TODO Auto-generated constructor stub
        result=nameList;
        oceny=ocenaList;
        wieki=wiekList;
        context=mainActivity;
        imageId=images;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView tvOcena;
        TextView tvWiek;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tvOcena=(TextView) rowView.findViewById(R.id.textView2);
        holder.tvWiek=(TextView) rowView.findViewById(R.id.textView3);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(result[position]);
        holder.tvOcena.setText(oceny[position]);
        holder.tvWiek.setText(wieki[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Zamówiono "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}