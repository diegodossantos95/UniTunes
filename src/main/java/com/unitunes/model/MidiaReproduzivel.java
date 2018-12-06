package com.unitunes.model;

import com.unitunes.mediaplayer.PlayableMedia;

public class MidiaReproduzivel extends Midia {

  public byte[] getMediaFile() {
    return this.getConteudo();
  }

  public String getExtension() {
    return this.getTipo();
  }
}
