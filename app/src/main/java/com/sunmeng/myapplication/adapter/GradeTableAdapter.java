package com.sunmeng.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by yuzhiyun on 2017-01-11.
 */
public class GradeTableAdapter extends BaseAdapter {
    private Context context;
    private List<TableRow> table;
    private TableRow tableRow ;

    public GradeTableAdapter(Context context , List<TableRow> table){
        this.context = context ;
        this.table = table ;
    }

    @Override
    public int getCount() {
        return table.size();
    }

    @Override
    public Object getItem(int i) {
        return table.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        tableRow = table.get(i);
        return new TableRowView(this.context, tableRow);
    }

    class TableRowView extends LinearLayout {
        public TableRowView(Context context, TableRow tableRow) {
            super(context);

            this.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i <  tableRow.getSize(); i++) {//逐个格单元添加到行
                TableCell tableCell = tableRow.getCellValue(i);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        tableCell.width, tableCell.height);//按照格单元指定的大小设置空间

                layoutParams.setMargins(0, 0, 1, 1);//预留空隙制造边框

                if (tableCell.type == TableCell.STRING) {//如果格单元是文本内容
                    TextView textCell = new TextView(context);
                    textCell.setLines(1);
                    textCell.setGravity(Gravity.CENTER);
                    textCell.setBackgroundColor(Color.WHITE);//背景白色
                    textCell.setText(String.valueOf(tableCell.value));
                    addView(textCell, layoutParams);
                } else if (tableCell.type == TableCell.IMAGE) {//如果格单元是图像内容
                    ImageView imgCell = new ImageView(context);
                    imgCell.setBackgroundColor(Color.BLACK);//背景黑色
                    imgCell.setImageResource((Integer) tableCell.value);
                    addView(imgCell, layoutParams);
                }
            }
//            this.setBackgroundColor(getResources().getColor(R.color.main_color));//背景白色，利用空隙来实现边框
            this.setBackgroundColor(Color.BLACK);
        }
    }

    static public class TableRow {
        private TableCell[] cell;
        public TableRow(TableCell[] cell) {
            this.cell = cell;
        }
        public int getSize() {
            return cell.length;
        }
        public TableCell getCellValue(int index) {
            if (index >= cell.length)
                return null;
            return cell[index];
        }
    }

    static public class TableCell {
        static public final int STRING = 0;
        static public final int IMAGE = 1;
        public Object value;
        public int width;
        public int height;
        private int type;
        public TableCell(Object value, int width, int height, int type) {
            this.value = value;
            this.width = width;
            this.height = height;
            this.type = type;
        }
    }
}
