package com.edu.example.assignmentprojectsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.edu.example.assignmentprojectsample.Dao.ThuThuDao;
import com.edu.example.assignmentprojectsample.Fragments.DoanhThuFragment;
import com.edu.example.assignmentprojectsample.Fragments.DoiMatKhauFragment;
import com.edu.example.assignmentprojectsample.Fragments.LoaiSachFragment;
import com.edu.example.assignmentprojectsample.Fragments.PhieuMuonFragment;
import com.edu.example.assignmentprojectsample.Fragments.SachFragment;
import com.edu.example.assignmentprojectsample.Fragments.ThanhVienFragment;
import com.edu.example.assignmentprojectsample.Fragments.ThemThuThuFragment;
import com.edu.example.assignmentprojectsample.Fragments.Top10Fragment;
import com.edu.example.assignmentprojectsample.Models.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    View mHeaderView;
    ThuThuDao thuThuDao;
    TextView tvUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolBar);
        frameLayout=findViewById(R.id.frameLayout);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawer_layoutT);


        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_icon);

        PhieuMuonFragment phieuMuonFragment=new PhieuMuonFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout,phieuMuonFragment)
                .commit();


        mHeaderView=navigationView.getHeaderView(0);
        tvUser=mHeaderView.findViewById(R.id.tvNavUser);
        Intent ilogin=getIntent();
        String user=ilogin.getStringExtra("user");
        thuThuDao=new ThuThuDao(this);
        ThuThu thuThu=thuThuDao.getID(user);
        String username=thuThu.hoTen;
        tvUser.setText("Xin chào "+username);

        if (user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.ndThemThuThu).setVisible(true);
        }






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager=getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.qlPhieuMuon:
                      PhieuMuonFragment phieuMuonFragment=new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,phieuMuonFragment)
                                .commit();
                        break;
                    case R.id.qlLoaiSach:
                       LoaiSachFragment loaiSachFragment=new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,loaiSachFragment)
                                .commit();
                        break;
                    case R.id.qlSach:
                       SachFragment sachFragment=new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,sachFragment)
                                .commit();
                        break;
                    case R.id.qlThanhVien:
                        ThanhVienFragment thanhVienFragment=new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,thanhVienFragment)
                                .commit();
                        break;
                    case R.id.tkTop10:
                       Top10Fragment top10Fragment=new Top10Fragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,top10Fragment)
                                .commit();
                        break;
                    case R.id.tkDoanhThu:
                       DoanhThuFragment doanhThuFragment=new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,doanhThuFragment)
                                .commit();
                        break;
                    case R.id.ndThemThuThu:
                        ThemThuThuFragment themThuThuFragment=new ThemThuThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,themThuThuFragment)
                                .commit();
                        break;
                    case R.id.ndDoiMatKhau:
                       DoiMatKhauFragment doiMatKhauFragment=new DoiMatKhauFragment();
                        manager.beginTransaction()
                                .replace(R.id.frameLayout,doiMatKhauFragment)
                                .commit();
                        break;
                    case R.id.ndLogout:
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        break;


                }


                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}