package sec07.exam04_mediaview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RootController implements Initializable {
	//컨트롤러 등록
	@FXML private MediaView mediaView;
	@FXML private ImageView imageView;
	@FXML private Button btnPlay;
	@FXML private Button btnPause;
	@FXML private Button btnStop;
	
	private boolean endOfMedia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Media 생성
		Media media = 
				new Media(getClass().getResource("media/video.m4v").toString());
		
		//MediaPlayer 생성
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
	
		//MediaPlayer READY --> 재생할 상태
		mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				//버튼 세팅
				btnPlay.setDisable(false); 
				btnPause.setDisable(true); 
				btnStop.setDisable(true);
				if(mediaPlayer.isAutoPlay()) {
					mediaView.setVisible(false);
				}//end if
			}//end run
		});
		//MediaPlayer  --> 재생 중 처리 람다식
		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true); 
			btnPause.setDisable(false); 
			btnStop.setDisable(false);
		});
		//MediaPlayer  --> 중지 람다식
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(false);
		});
		//MediaPlayer  --> 재생끝 람다식
		mediaPlayer.setOnEndOfMedia(()->{
			endOfMedia = true;
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(true);
		});
		//MediaPlayer  --> 정지 람다식
		mediaPlayer.setOnStopped(()->{
			mediaPlayer.seek(mediaPlayer.getStartTime());
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
			btnStop.setDisable(true);
		});
		
		//버튼 ActionEvent 처리
		btnPlay.setOnAction(event->{
			if(endOfMedia) { 
				//재생중지
				mediaPlayer.stop(); 
				//재생 시간 처음으로 돌림
				mediaPlayer.seek(mediaPlayer.getStartTime());
			}//end if
			//미디어 재생
			mediaPlayer.play();
			endOfMedia = false;
		});
		//미디어 일시 중디
		btnPause.setOnAction(event->mediaPlayer.pause());
		//미디어 중지
		btnStop.setOnAction(event->mediaPlayer.stop());
	}//end initialize
}//end class
