package sec07.exam05_slider_progressbar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class RootController implements Initializable {
	@FXML private MediaView mediaView;
	@FXML private ImageView imageView;
	@FXML private Button btnPlay;
	@FXML private Button btnPause;
	@FXML private Button btnStop;
	
	@FXML private Label labelTime;	
	@FXML private Slider sliderVolume;
	@FXML private ProgressBar progressBar;
	@FXML private ProgressIndicator progressIndicator;
	
	private boolean endOfMedia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Media 생성
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		
		//MediaPlayer 생성
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
	
		//MediaPlayer Ready 설정
		mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				//MediaPlayer 리스너 설정 --> 재생시간 progressBar, progressIndicator, label 반영
				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
					@Override
					public void changed(
							ObservableValue<? extends Duration> observable, 
							Duration oldValue, Duration newValue) {
						//재생시간을 나타내기 위해서 현재 재생 시간을 전체 재생시간으로 나눔
							double progress = 
								mediaPlayer
								.getCurrentTime()
								.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
							//progressBar, progressIndicator 값 설정
							progressBar.setProgress(progress);
							progressIndicator.setProgress(progress);
							//라벨 값 설정
							labelTime.setText(
								(int)mediaPlayer.getCurrentTime().toSeconds()+"/"+
								(int)mediaPlayer.getTotalDuration().toSeconds()+" sec");	
						}//end changed
					}//end addListener
				); //end addListener

				//버튼 설정
				btnPlay.setDisable(false); 
				btnPause.setDisable(true); 
				btnStop.setDisable(true);
				if(mediaPlayer.isAutoPlay()) {
					mediaView.setVisible(false);
				}//end if
			}//end run
		});//end setOnReady
		
		//MediaPlayer 재생중
		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true); 
			btnPause.setDisable(false); 
			btnStop.setDisable(false);
		});
		//MediaPlayer 중지
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(false);
		});
		//MediaPlayer 재생끝
		mediaPlayer.setOnEndOfMedia(()->{
			progressBar.setProgress(1.0);
			progressIndicator.setProgress(1.0);			
			endOfMedia = true;
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(true);
		});
		//MediaPlayer 정지
		mediaPlayer.setOnStopped(()->{
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(true);
		});
		
		sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mediaPlayer.setVolume(sliderVolume.getValue() / 100.0);
			}//end changed
		});
		sliderVolume.setValue(50.0);
		
		//버튼 이벤트 설정
		btnPlay.setOnAction(event->{
			if(endOfMedia) { 
				mediaPlayer.stop();
				mediaPlayer.seek(mediaPlayer.getStartTime());
			}
			mediaPlayer.play();
			endOfMedia = false;
		});
		btnPause.setOnAction(event->mediaPlayer.pause());
		btnStop.setOnAction(event->mediaPlayer.stop());
	}
}//end class
