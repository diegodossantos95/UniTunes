package com.unitunes.mediaplayer;

import com.unitunes.mediaplayer.strategy.MP3Player;
import com.unitunes.mediaplayer.strategy.MP4Player;
import com.unitunes.mediaplayer.strategy.MediaPlayerAlgorithm;
import com.unitunes.mediaplayer.strategy.WMVPlayer;
import com.unitunes.model.MidiaReproduzivel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.springframework.stereotype.Component;

@Component
public class MediaPlayerImpl implements MediaPlayer {

  private static MediaPlayer instance;

  private Queue<MidiaReproduzivel> queue;

  private Queue<MidiaReproduzivel> previousQueue;

  private MediaPlayerAlgorithm currentPlayer;

  private MediaPlayerImpl() {
    queue = new LinkedList<>();
    previousQueue = new LinkedList<>();
  }

  public static MediaPlayer getInstance() {
    if (instance == null) {
      return new MediaPlayerImpl();
    }

    return instance;
  }

  @Override
  public void play(MidiaReproduzivel media) {
    currentPlayer = resolvePlayerAlgorithm(media.getExtension());
    currentPlayer.play(media);
  }

  @Override
  public void pause() {
    if (currentPlayer != null) {
      currentPlayer.pause();
    }
  }

  @Override
  public void next() {
    MidiaReproduzivel nextMedia = queue.poll();

    if (nextMedia != null) {
      this.play(nextMedia);
    }
  }

  @Override
  public void previous() {
    MidiaReproduzivel previousMedia = previousQueue.poll();

    if (previousMedia != null) {
      this.play(previousMedia);
    }
  }

  @Override
  public void enqueue(MidiaReproduzivel media) {
    this.queue.offer(media);
  }

  private MediaPlayerAlgorithm resolvePlayerAlgorithm(String extension) {
    switch (extension) {
      case "mp3":
        return new MP3Player();

      case "mp4":
        return new MP4Player();

      case "wmv":
        return new WMVPlayer();

      default:
        throw new RuntimeException("Formato de mídia não reconhecido");
    }
  }
}
