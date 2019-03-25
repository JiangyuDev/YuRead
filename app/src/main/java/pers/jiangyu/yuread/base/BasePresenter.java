package pers.jiangyu.yuread.base;

public abstract class BasePresenter<V,M> {

    protected V view;

    protected M model;

    public BasePresenter(V view){
        this.view = view;
    }

}
