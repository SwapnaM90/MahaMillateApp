package com.mninetytechnology.mahamillateapp.acitivities.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mninetytechnology.mahamillateapp.acitivities.ui.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.videos.VideosActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentHomeBinding;

import org.eazegraph.lib.models.PieModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

       /* Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getContext(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Grain", 100));
        data.add(new ValueDataEntry("Money", 200));

        pie.data(data);

        pie.palette().itemAt(0,new SolidFill("#D7AF19",1));
        pie.palette().itemAt(1,new SolidFill("#AE5D57",1));

        pie.title("Total donations made");*/

        binding.pieChart.addPieSlice(
                new PieModel(
                        "Grain",
                        400,
                        Color.parseColor("#D7AF19")));
        binding.pieChart.addPieSlice(
                new PieModel(
                        "Money",
                        600,
                        Color.parseColor("#AE5D57")));

        // To animate the pie chart
        binding.pieChart.startAnimation();

        binding.llVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideosActivity();
            }
        });

        binding.imgVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideosActivity();
            }
        });

        binding.tvVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideosActivity();
            }
        });

        binding.llBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.tvBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.llQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        binding.imgQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        binding.tvQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });
//
//        pie.labels().position("outside");
//
//        pie.legend().title().enabled(true);
//        pie.legend().title()
//                .text("Retail channels")
//                .padding(0d, 0d, 10d, 0d);
//
//        pie.legend()
//                .position("center-bottom")
//                .itemsLayout(LegendLayout.HORIZONTAL)
//                .align(Align.CENTER);

//        binding.pieChart.setChart(pie);

        return binding.getRoot();
    }

    private void openVideosActivity() {
        Intent intent = new Intent(getActivity(), VideosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openBlogActivity() {
        Intent intent = new Intent(getActivity(), BlogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openQuizActivity() {
        Intent intent = new Intent(getActivity(), QuizMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}