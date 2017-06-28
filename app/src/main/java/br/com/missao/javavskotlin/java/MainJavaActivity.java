package br.com.missao.javavskotlin.java;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.missao.javavskotlin.R;
import br.com.missao.javavskotlin.kotlin.adapters.RedditNewsAdater;
import br.com.missao.javavskotlin.kotlin.api.RedditNewsDataResponse;
import br.com.missao.javavskotlin.kotlin.domains.MainDomain;
import java.util.List;

/**
 * MainScreen Java Controller
 */
public class MainJavaActivity extends AppCompatActivity {

  /**
   * Title TextView
   */
  private TextView textTitle;

  /**
   * ClickMe Button
   */
  private Button btnClickMe;

  /**
   * Reddit News Recycler View
   */
  private RecyclerView recyclerView;

  /**
   * Deals with Activity Requests
   */
  private MainDomain domain = MainDomain.INSTANCE;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    loadViews();
  }

  /**
   * Initiates Controller Views
   */
  private void loadViews() {
    bindViews();
    setupContent();
    bindEvents();
  }

  /**
   * Links Layout views to Controller
   */
  private void bindViews() {
    textTitle = (TextView) findViewById(R.id.textTitle);
    btnClickMe = (Button) findViewById(R.id.btnClickMe);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerNews);
  }

  /**
   * Loads Views Initial Contents
   */
  private void setupContent() {
    textTitle.setText(getString(R.string.instructions));
  }

  /**
   * Binds View Events
   */
  private void bindEvents() {
    btnClickMe.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        requestRedditNews();
      }
    });
  }

  /**
   * Request Reddit's most relevant news
   */
  private void requestRedditNews() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        List<RedditNewsDataResponse> data = domain.requestRedditNews();
        setupRecyclerView(data);
      }
    };

    Handler handler = new Handler();
    handler.post(runnable);
  }

  /**
   * Sets up recyclerView
   */
  private void setupRecyclerView(List<RedditNewsDataResponse> data) {
    if (data != null) {
      textTitle.setVisibility(View.GONE);
      btnClickMe.setVisibility(View.GONE);
      recyclerView.setLayoutManager(
          new LinearLayoutManager(recyclerView.getContext()));
      recyclerView.setHasFixedSize(true);
      recyclerView.setAdapter(new RedditNewsAdater(data));
      recyclerView.setVisibility(View.VISIBLE);
    }
  }
}
