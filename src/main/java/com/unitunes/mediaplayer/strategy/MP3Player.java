package com.unitunes.mediaplayer.strategy;

import com.unitunes.model.MidiaReproduzivel;

public class MP3Player implements MediaPlayerAlgorithm {

  @Override
  public void play(MidiaReproduzivel media) {
    System.out.printf("Reproduzindo midia MP3: %s - %s", media.getNome(), media.getDescricao());
  }

  @Override
  public void pause() {
    System.out.printf("Reprodução de MP3 pausada.");
  }
}
