package com.liuxv.databinding.mvc.base;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public interface Action {
  void execute();

  public static class EmptyAction implements Action {
    @Override
    public void execute() {}
  }
}
