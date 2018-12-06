package com.unitunes.mediaplayer.strategy;

import com.unitunes.model.MidiaReproduzivel;

public class WMVPlayer implements MediaPlayerAlgorithm {

  @Override
  public void play(MidiaReproduzivel media) {
    System.out.printf("Reproduzindo midia WMV: %s - %s", media.getNome(), media.getDescricao());
  }

  @Override
  public void pause() {
    System.out.printf("Reprodução de WMV pausada.");
  }
}
