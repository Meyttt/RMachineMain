
package Memories;
/**
 * Created by Anton on 16.10.2016.
 */
public interface Memory {
    String type = "memory";
    String read(String... args);
    boolean write(String... args);
    boolean clear();
    String getName();
    int size();

    boolean addNewStr(int index, String value);

    boolean insertNewStr(int index, String value);

    boolean searchTrue(String value);

    boolean searchFalse(String value);
}
