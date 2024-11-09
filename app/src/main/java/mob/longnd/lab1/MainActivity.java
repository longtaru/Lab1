package mob.longnd.lab1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import mob.longnd.lab1.DAO.TaskDAO;
import mob.longnd.lab1.adapter.TaskAdapter;
import mob.longnd.lab1.model.Task;


public class MainActivity extends AppCompatActivity {
    EditText edID,edTitle,edContent,edDate,edType;
    Button btnAdd,btnUpdate,btnDelete;
    ListView lvTask;
    TaskDAO taskDAO;
    TaskAdapter adapter = null;
    ArrayList<Task> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       taskDAO= new TaskDAO(this);
        anhxa();

        list =taskDAO.getAllTasks();
        adapter = new TaskAdapter(list,this);
        lvTask.setAdapter(adapter);

        lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, list.get(position).toString(), Toast.LENGTH_SHORT).show();
                Task task = list.get(position);
                edID.setText(task.getId()+"");
                edTitle.setText(task.getTitle());
                edContent.setText(task.getContent());
                edDate.setText(task.getDate());
                edType.setText(task.getType());
                return false;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString();
                String content = edContent.getText().toString();
                String date = edDate.getText().toString();
                String type = edType.getText().toString();
                if(title.isEmpty() || content.isEmpty() || date.isEmpty() || type.isEmpty()){
                    Toast.makeText(MainActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    if(title.isEmpty()){
                        edTitle.setError("Không được để trống");
                    }
                    if(content.isEmpty()){
                        edTitle.setError("Không được để trống");
                    }
                    if(date.isEmpty()){
                        edTitle.setError("Không được để trống");
                    }
                    if(type.isEmpty()){
                        edTitle.setError("Không được để trống");
                    }
                }else {
                    Task task = new Task(1, title, content, date, type);
                    long check = taskDAO.addTask(task);
                    if(check < 0) {
                        Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    list =taskDAO.getAllTasks();
                    adapter = new TaskAdapter(list,MainActivity.this);
                    lvTask.setAdapter(adapter);
                    reset();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String id = edID.getText().toString();
               if(id.isEmpty()){
                   Toast.makeText(MainActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
               }else {

                   String title = edTitle.getText().toString();
                   String content = edContent.getText().toString();
                   String date = edDate.getText().toString();
                   String type = edType.getText().toString();

                   Task task=new Task(Integer.parseInt(id),title,content,date,type);
                   long check = taskDAO.updateTask(task);
                   if(check <0){
                       Toast.makeText(MainActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                   }
                   list =taskDAO.getAllTasks();
                   adapter = new TaskAdapter(list,MainActivity.this);
                   lvTask.setAdapter(adapter);
                   reset();
               }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edID.getText().toString();
                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else {

                    long check = taskDAO.deleteTask(Integer.parseInt(id));
                    if(check <0){
                        Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                    list =taskDAO.getAllTasks();
                    adapter = new TaskAdapter(list,MainActivity.this);
                    lvTask.setAdapter(adapter);
                    reset();
                }

            }
        });

    }

    public void anhxa(){
        edID =findViewById(R.id.edID);
        edTitle =findViewById(R.id.edTitle);
        edContent =findViewById(R.id.edContent);
        edDate =findViewById(R.id.edDate);
        edType =findViewById(R.id.edType);
        btnAdd =findViewById(R.id.btnADD);
        btnUpdate =findViewById(R.id.btnUpdate);
        btnDelete =findViewById(R.id.btnDelete);
        lvTask =findViewById(R.id.lvTask);

    }

    public void reset() {
        edID.setText("");
        edTitle.setText("");
        edContent.setText("");
        edDate.setText("");
        edType.setText("");
    }
}