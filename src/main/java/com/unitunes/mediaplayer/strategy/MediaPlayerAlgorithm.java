package com.unitunes.mediaplayer.strategy;

import com.unitunes.model.MidiaReproduzivel;

public interface MediaPlayerAlgorithm {
  void play(MidiaReproduzivel media);
  void pause();
}
