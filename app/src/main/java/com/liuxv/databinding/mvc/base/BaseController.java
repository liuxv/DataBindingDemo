package com.liuxv.databinding.mvc.base;

/**
 * Base for all controllers.
 * This interface is to make a standard for all controllers. Each controller should be bind with
 * BaseView and BaseModel.
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public abstract class BaseController<V extends BaseView, M extends BaseModel> {
  /**
   * Bind the view and model.
   *
   * @param view view
   * @param model model
   */
  public abstract void bind(V view, M model);

  /**
   * Initialize/reset the view and model for reuse, called before bind in ListView , called when
   * view recycle in RecyclerView.
   * 
   * Your need override this method if something is running when view is recycle.
   */
  public void unbind() {};

}
