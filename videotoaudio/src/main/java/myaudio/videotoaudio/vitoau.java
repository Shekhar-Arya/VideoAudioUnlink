package myaudio.videotoaudio;

import java.io.File;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;


public class vitoau 
{

	public void extractAudioFromVedio()
	{
	    String videoFolder="E:\\Favourite\\Fav. video\\";
	    //video file
	    String videoFile = "Sample MP4 Video File for Testing.mp4";
	    //path
	    String videoPath = videoFolder + videoFile;
	    //audio path
	    String extractAudio=videoFolder+"videogen\\extraxAudio.mp3";
	    try{
	        //check the audio file exist or not ,remove it if exist
	        File extractAudioFile = new File(extractAudio);
	        if (extractAudioFile.exists()) {
	            extractAudioFile.delete();
	        }
	        //audio recorder，extractAudio:audio path，2:channels 
	        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(extractAudio, 2);
	        recorder.setAudioOption("crf", "0");
	        recorder.setAudioQuality(0.0);
	        //bit rate
	        recorder.setAudioBitrate(64000);
	        //sample rate
	        recorder.setSampleRate(44100);
	        recorder.setAudioChannels(2);
	        //encoder
	        recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3);
	        //start
	        recorder.start();
	        //load video
	        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoPath);
	        grabber.start();
	        Frame f=null;
	        //get audio sample and record it
	        while ((f = grabber.grabSamples()) != null) {
	        recorder.record(f);
	        }
	        // stop to save
	        grabber.stop();
	        recorder.close();
	        //output audio path
	        Logger l = new Logger(); 
	        l.info(extractAudio);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
}
