package com.unitunes.mediaplayer.strategy;

import com.unitunes.model.MidiaReproduzivel;

public class MP4Player implements MediaPlayerAlgorithm {

  @Override
  public void play(MidiaReproduzivel media) {
    System.out.printf("Reproduzindo midia MP4: %s - %s", media.getNome(), media.getDescricao());
  }

  @Override
  public void pause() {
    System.out.printf("Reprodução de MP4 pausada.");
  }
}
