package com.mikepenz.iconics.context;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

class InternalLayoutInflater extends LayoutInflater {
    private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.webkit."};
    private Field mConstructorArgs = null;
    private final IconicsFactory mIconicsFactory = new IconicsFactory();
    private boolean mSetPrivateFactory = false;

    @TargetApi(11)
    private static class WrapperFactory2 implements Factory2 {
        protected final Factory2 mFactory2;
        protected final IconicsFactory mIconicsFactory;

        public WrapperFactory2(Factory2 factory2, IconicsFactory iconicsFactory) {
            this.mFactory2 = factory2;
            this.mIconicsFactory = iconicsFactory;
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return this.mIconicsFactory.onViewCreated(this.mFactory2.onCreateView(name, context, attrs), context, attrs);
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.mIconicsFactory.onViewCreated(this.mFactory2.onCreateView(parent, name, context, attrs), context, attrs);
        }
    }

    @TargetApi(11)
    private static class PrivateWrapperFactory2 extends WrapperFactory2 {
        private final InternalLayoutInflater mInflater;

        public PrivateWrapperFactory2(Factory2 factory2, InternalLayoutInflater inflater, IconicsFactory iconicsFactory) {
            super(factory2, iconicsFactory);
            this.mInflater = inflater;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.mIconicsFactory.onViewCreated(this.mInflater.createCustomViewInternal(parent, this.mFactory2.onCreateView(parent, name, context, attrs), name, context, attrs), context, attrs);
        }
    }

    private static class WrapperFactory implements Factory {
        private final Factory mFactory;
        private final IconicsFactory mIconicsFactory;
        private final InternalLayoutInflater mInflater;

        public WrapperFactory(Factory factory, InternalLayoutInflater inflater, IconicsFactory iconicsFactory) {
            this.mFactory = factory;
            this.mInflater = inflater;
            this.mIconicsFactory = iconicsFactory;
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return this.mIconicsFactory.onViewCreated(this.mFactory.onCreateView(name, context, attrs), context, attrs);
        }
    }

    protected InternalLayoutInflater(Context context) {
        super(context);
        setUpLayoutFactories(false);
    }

    protected InternalLayoutInflater(LayoutInflater original, Context newContext, boolean cloned) {
        super(original, newContext);
        setUpLayoutFactories(cloned);
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return new InternalLayoutInflater(this, newContext, true);
    }

    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        View view = null;
        for (String prefix : sClassPrefixList) {
            try {
                view = createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
            }
        }
        if (view == null) {
            view = super.onCreateView(name, attrs);
        }
        return this.mIconicsFactory.onViewCreated(view, view.getContext(), attrs);
    }

    protected View onCreateView(View parent, String name, AttributeSet attrs) throws ClassNotFoundException {
        return this.mIconicsFactory.onViewCreated(super.onCreateView(parent, name, attrs), getContext(), attrs);
    }

    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        setPrivateFactoryInternal();
        return super.inflate(parser, root, attachToRoot);
    }

    private void setUpLayoutFactories(boolean cloned) {
        if (!cloned && getFactory2() != null && !(getFactory2() instanceof WrapperFactory2)) {
            setFactory2(getFactory2());
        }
    }

    public void setFactory(Factory factory) {
        if (factory instanceof WrapperFactory) {
            super.setFactory(factory);
        } else {
            super.setFactory(new WrapperFactory(factory, this, this.mIconicsFactory));
        }
    }

    @TargetApi(11)
    public void setFactory2(Factory2 factory2) {
        if (factory2 instanceof WrapperFactory2) {
            super.setFactory2(factory2);
        } else {
            super.setFactory2(new WrapperFactory2(factory2, this.mIconicsFactory));
        }
    }

    private void setPrivateFactoryInternal() {
        if (!this.mSetPrivateFactory) {
            if (getContext() instanceof Factory2) {
                Method setPrivateFactoryMethod = ReflectionUtils.getMethod(LayoutInflater.class, "setPrivateFactory");
                if (setPrivateFactoryMethod != null) {
                    ReflectionUtils.invokeMethod(this, setPrivateFactoryMethod, new PrivateWrapperFactory2((Factory2) getContext(), this, this.mIconicsFactory));
                }
                this.mSetPrivateFactory = true;
                return;
            }
            this.mSetPrivateFactory = true;
        }
    }

    private View createCustomViewInternal(View parent, View view, String name, Context viewContext, AttributeSet attrs) {
        if (!true) {
            return view;
        }
        if (view == null && name.indexOf(46) > -1) {
            if (this.mConstructorArgs == null) {
                this.mConstructorArgs = ReflectionUtils.getField(LayoutInflater.class, "mConstructorArgs");
            }
            Object[] mConstructorArgsArr = (Object[]) ReflectionUtils.getValue(this.mConstructorArgs, this);
            Object lastContext = mConstructorArgsArr[0];
            mConstructorArgsArr[0] = viewContext;
            ReflectionUtils.setValue(this.mConstructorArgs, this, mConstructorArgsArr);
            try {
                view = createView(name, null, attrs);
            } catch (ClassNotFoundException e) {
            } finally {
                mConstructorArgsArr[0] = lastContext;
                ReflectionUtils.setValue(this.mConstructorArgs, this, mConstructorArgsArr);
            }
        }
        return view;
    }
}
