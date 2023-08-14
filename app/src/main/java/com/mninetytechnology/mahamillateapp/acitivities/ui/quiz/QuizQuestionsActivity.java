package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizQuestionsBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizQuestionContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Questions;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizScore;
import com.mninetytechnology.mahamillateapp.presenter.QuizQuestionsPresenter;

import java.util.List;

public class QuizQuestionsActivity extends BaseActivity implements QuizQuestionContract.ViewModel {
    private List<Questions> questionsList;
    private int quesNum;
    public CountDownTimer countDown;
    private int score;
    private int incorrect;

    private boolean isImage = false;
//    private int unans;

    private QuizScore quizScore;
    private ActivityQuizQuestionsBinding binding;

    private QuizQuestionsPresenter presenter;

    String selectedQuestion = "";

    private int tot_timer_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quiz_questions);
        presenter = new QuizQuestionsPresenter(this,this);
        binding.setPresenter(presenter);
        presenter.loadQuiz();

        score = 0;
        quizScore = new QuizScore();
        incorrect = 0;

        String user_class = getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserClass();
        if (!user_class.trim().isEmpty()) {
            if (user_class.contains("1-4")) {
                tot_timer_time = 90000;
            } else if (user_class.contains("5-7")) {
                tot_timer_time = 80000;
            } else if (user_class.contains("8-10")) {
                tot_timer_time = 70000;
            } else {
                tot_timer_time = 60000;
            }
        } else {
            tot_timer_time = 100000;
        }
    }

    private void starttimer() {
//        binding.progressBar.setMax(tot_timer_time);
        countDown =new CountDownTimer(tot_timer_time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished<tot_timer_time) {
                    binding.textViewProgress.setText(String.valueOf(millisUntilFinished / 1000));
                    binding.progressBar.setProgress((int) (millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };
        countDown.start();
    }

    @Override
    public void checkAnswer(String selectedOption, View view){
        if (countDown != null) {
            countDown.cancel();
        }
        this.selectedQuestion = selectedOption;
        if (isImage) {
            binding.imgOption1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.imgOption2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.imgOption3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.imgOption4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#855D25")));
        } else {
            binding.txtOption1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.txtOption2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.txtOption3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            binding.txtOption4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
            view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#855D25")));
        }
    }

    @Override
    public void setQuestions(List<Questions> questions) {
        questionsList = questions;
        isImage = questions.get(0).isImage();
        binding.textViewProgress.setText(String.valueOf(10));
        if (isImage) {
            setImageQuestion(0);
        } else {
            setTextQuestion(0);
        }
        binding.tvCurrentQuestion.setText(String.valueOf(1));
        binding.tvTotalQuestion.setText(String.valueOf(questionsList.size()));
        binding.pbLevel.setMax(questionsList.size());
        starttimer();
        quesNum=0;
    }

    /**
     * set question as a image format
     */
    private void setImageQuestion(int position) {
        binding.tvQuestion.setVisibility(View.GONE);
        binding.imgQuestion.setVisibility(View.GONE);

        Glide.with(QuizQuestionsActivity.this)
                .load(questionsList.get(position).getQuestion())
                .into(binding.imgQuestion);

        setUpOptions(questionsList.get(position).getOptions().isImage(),position);
    }

    /**
     * set up question option
     *
     * @param image
     * @param position
     */
    private void setUpOptions(boolean image, int position) {
        if (questionsList.get(position).getOptions().isImage()) {
            binding.llOptionText.setVisibility(View.GONE);
            binding.scvOptionImage.setVisibility(View.VISIBLE);

            Glide.with(QuizQuestionsActivity.this)
                    .load(questionsList.get(position).getOptions())
                    .into(binding.imgQuestion);

            Glide.with(QuizQuestionsActivity.this)
                    .load(questionsList.get(position).getOptions().getA())
                    .into(binding.imgOption1);

            Glide.with(QuizQuestionsActivity.this)
                    .load(questionsList.get(position).getOptions().getB())
                    .into(binding.imgOption2);

            Glide.with(QuizQuestionsActivity.this)
                    .load(questionsList.get(position).getOptions().getC())
                    .into(binding.imgOption3);

            Glide.with(QuizQuestionsActivity.this)
                    .load(questionsList.get(position).getOptions().getD())
                    .into(binding.imgOption4);


        } else {
            binding.llOptionText.setVisibility(View.VISIBLE);
            binding.scvOptionImage.setVisibility(View.GONE);
            binding.tvQuestion.setText(questionsList.get(position).getQuestion());
            binding.txtOption1.setText(questionsList.get(position).getOptions().getA());
            binding.txtOption2.setText(questionsList.get(position).getOptions().getB());
            binding.txtOption3.setText(questionsList.get(position).getOptions().getC());
            binding.txtOption4.setText(questionsList.get(position).getOptions().getD());
        }
    }

    /**
     * set question as a text format
     */
    private void setTextQuestion(int position) {
        binding.tvQuestion.setVisibility(View.VISIBLE);
        binding.imgQuestion.setVisibility(View.GONE);
        setUpOptions(questionsList.get(position).getOptions().isImage(),position);
    }


    @Override
    public void showFailed(String error) {
        showErrorSnackBar(binding.getRoot(),error);
    }

    @Override
    public void changeQuestion(){
        //if (quesNum<questionsList.size()-1) {
            if (!selectedQuestion.trim().isEmpty()) {
                if (selectedQuestion.equalsIgnoreCase(questionsList.get(quesNum).getAnswer())) {
                    score++;
                } else {
                    incorrect++;
                }
            }
            quesNum++;
        if (quesNum < questionsList.size()) {
            if (quesNum == questionsList.size() -1) {
                binding.btnNext.setText(getString(R.string.finish));
            }
            if (isImage) {
                setImageQuestion(quesNum);
            } else {
                playAnim(binding.tvQuestion,0,0);
                playAnim(binding.txtOption1,0,1);
                playAnim(binding.txtOption2,0,2);
                playAnim(binding.txtOption3,0,3);
                playAnim(binding.txtOption4,0,4);
            }

            int quesNumFinal = quesNum + 1;
            binding.tvCurrentQuestion.setText(String.valueOf(quesNumFinal));
            binding.pbLevel.setProgress(quesNumFinal);
            binding.tvTotalQuestion.setText(String.valueOf(questionsList.size()));
            binding.textViewProgress.setText(String.valueOf(36));
            selectedQuestion = "";
            starttimer();
        }else{
            int tot_questions = questionsList.size();
            int answered = score + incorrect;
            int not_answered = tot_questions - answered;
            quizScore.setScore(score);
            quizScore.setIncorrect_questions(incorrect);
            quizScore.setTot_questions(tot_questions);
            quizScore.setNot_answered_questions(not_answered);
            //go to score activity
            Intent intent = new Intent(QuizQuestionsActivity.this, QuizScoreActivity.class);
            intent.putExtra(AppKeys.SCORE, quizScore);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            this.finish();
        }
    }
    private void playAnim(View view,final int value, int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if (value==0)
                        {
                            switch (viewNum){
                                case 0:
                                    ((TextView)view).setText(questionsList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((TextView)view).setText(questionsList.get(quesNum).getOptions().getA());
                                    break;
                                case 2:
                                    ((TextView)view).setText(questionsList.get(quesNum).getOptions().getB());
                                    break;
                                case 3:
                                    ((TextView)view).setText(questionsList.get(quesNum).getOptions().getC());
                                    break;
                                case 4:
                                    ((TextView)view).setText(questionsList.get(quesNum).getOptions().getD());
                                    break;
                            }

                            if (viewNum!=0) {
                                view.setBackgroundResource(R.drawable.round_corner);
                                ((TextView) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F4F4")));
                                GradientDrawable myGrad = (GradientDrawable)((TextView) view).getBackground();
                                myGrad.setStroke(convertDpToPx(3), Color.BLACK);
                            }
                            playAnim(view,1,viewNum);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    private int convertDpToPx(int dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        QuizQuestionsActivity.super.onBackPressed();
                        countDown.cancel();
                    }
                }).create().show();

    }
}