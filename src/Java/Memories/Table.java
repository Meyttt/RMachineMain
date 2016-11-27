package Memories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by master on 24.10.2016.
 */
public class Table implements Memory {

    private String tname;
    ArrayList<ArrayList<String>> table = new ArrayList<>();
    ArrayList<String> colnames = new ArrayList<>();
    private int strnumber=0;
    private int colnumber=0;
//    private int columns;

    public Table(String tname, ArrayList<ArrayList<String>> table, ArrayList<String> colnames) {
        this.tname = tname;
        this.table = table;
        this.colnames = colnames;
    }

    public Table(String tname, ArrayList<String> colnames) {
        this.tname = tname;
        this.colnames = colnames;
        for(int count = 0; count < 1; count++) {
            table.add(count, colnames);
//            table.get(count).add("");
        }
    }
    public Table(String tname,ArrayList<ArrayList<String>> table,String[] colnames) {
        this.tname = tname;
        if(table!=null) {
            this.table = table;
        }
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, colnames);
        this.colnames = list;
    }

    public Table(String tname) {
        this.tname = tname;
        this.strnumber = 0;
        this.colnumber = 0;
    }

    public String getName() {
        return tname;
    }

    public int getColumns() {
        return colnames.size();
    }

    public int getStrNumber() {
        return strnumber;
    }

    public int getColNumber() {
        return colnumber;
    }

    ArrayList getString(int num) {
        return this.table.get(num);
    }

    public int size() { return this.table.size(); }
    //TODO: для работы с памятью нам необходимо чтение по столбцу. Иначе говоря, обращаться по имени столбца к текущей строке
    public String read(String... args) {
        return this.table.get(strnumber).get(colnumber);
    }

    @Override
    public boolean write(String... args) {
        if(this.table.size()==0){
            this.table.add(new ArrayList<>());
        }
            this.table.get(strnumber).add(colnumber, args[0]);

        return (this.table.get(strnumber).get(colnumber) != null);
    }

    @Override
    public boolean clear() {
        table.clear();
        return table.isEmpty();
    }

//    public boolean write(String value) {
//        this.table.get(strnumber).add(colnumber, value);
//        return (this.table.get(strnumber).get(colnumber) != null);
//    }

    public boolean addNewStr(String value) {
        this.table.add(colnames);
        this.table.get(this.table.size()-1).add(value);
        colnumber = this.table.size()-1;
        return (this.table.get(this.table.size()-1).get(colnumber) != null);
    }
    public boolean insertNewStr(String value) {
        this.table.add(strnumber-1,colnames);
        this.table.get(strnumber-1).add(value);
        colnumber = this.table.get(strnumber-1).size()-1;
        return (this.table.get(strnumber-1).get(colnumber) != null);
    }

    public boolean searchTrue(String value) {
//        this.write();

        int i, j = 0;
        for (i = 0; i < this.table.size(); i++)
            for (j = 0; j < this.table.get(i).size(); j++)
                if (Objects.equals(this.table.get(i).get(j), value)) {
                    this.strnumber = i;
                    this.colnumber = j;
                    return true;
                }
        return (i == (this.table.size() - 1) && j == (this.table.get(i).size()-1) && this.strnumber != i && this.colnumber != j);
    }

//    public boolean searchTrue(int searchcolumn, String value) {
//        int i;
//        for (i = 0; i < this.table.size(); i++)
//            if (Objects.equals(this.table.get(i).get(searchcolumn), value)) {
//                this.strnumber = i;
//                this.colnumber = searchcolumn;
//                break;
//            }
//        return !(i == (this.table.size() - 1) && this.strnumber != i);
//    }

    public boolean searchFalse(String value) {
        int i, j = 0;
        for (i = 0; i < this.table.size(); i++)
            for (j = 0; j < this.table.get(i).size(); j++)
                if (Objects.equals(this.table.get(i).get(j), value)) {
                    this.strnumber = i;
                    this.colnumber = j;
                    break;
                }
        return i == (this.table.size() - 1) && j == this.table.get(i).size() && this.strnumber != i && this.colnumber != j;
    }

//    public boolean searchFalse(int searchcolumn, String value) {
//        int i;
//        for (i = 0; i < this.table.size(); i++)
//            if (Objects.equals(this.table.get(i).get(searchcolumn), value)) {
//                this.strnumber = i;
//                this.colnumber = searchcolumn;
//                break;
//            }
//        return i == (this.table.size() - 1) && this.strnumber != i;
//    }

    public String toString(){
        String answer=null;
        try{
            answer="table: "+tname+"; value: "+table.toString();
        }catch (NullPointerException e){
            answer= "table: "+tname+"; value: null";
        }
        return answer;
    }

}
