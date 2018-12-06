package com.unitunes.mediaplayer;

import com.unitunes.model.MidiaReproduzivel;

public interface MediaPlayer {
  void play(MidiaReproduzivel media);
  void pause();
  void next();
  void previous();
  void enqueue(MidiaReproduzivel media);
}
