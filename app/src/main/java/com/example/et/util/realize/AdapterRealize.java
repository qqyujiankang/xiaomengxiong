package com.example.et.util.realize;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;


import com.example.et.entnty.Pagebean;
import com.example.et.util.lifeful.Lifeful;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Created by Administrator on 2017/12/11 0011.
 */
//, AdapterView.OnItemClickListener
public class AdapterRealize implements AdapterModel {
    private final static String ROOT = "pinyunkeji.hzwl.syhzb.view.adapter.";

    public final static String ManagerAdapter = ROOT + "ManagerAdapter";

    private List<Object> objectList = null;
    private BaseAdapter baseAdapter = null;

    /**
     * @param page
     */
    @Override
    public Integer AdapterSetListDatas(Pagebean<Object> objectPagebean, Integer page, ListView listView, Context mContext, Class<?> cls, Lifeful lifeful) {
        try {

            if (objectList == null) {
                objectList = objectPagebean.getList();
                page++;
            } else if (objectList != null) {
                if (page == 0) {
                    objectList.clear();
                }
                if (objectPagebean.getList().size() > 0) {
                    objectList.addAll(objectPagebean.getList());
                    page++;
                }
            }

          /*
            if (objectPagebean.getList().size() > 0) {
                if (objectList == null) {
                    objectList = objectPagebean.getList();
                } else if (objectList != null) {
                    if (page == 1) {
                        objectList.clear();
                    }
                    objectList.addAll(objectPagebean.getList());
                }
                page++;
            }*/


            if (baseAdapter != null) {
                baseAdapter.notifyDataSetChanged();
            } else {
                Class aClass1 = Class.forName(cls.getName());
                //对应的构造函数
                Constructor meth = aClass1.getConstructor(Context.class, List.class, Lifeful.class);
                //对应的构造函数的参数
                baseAdapter = (BaseAdapter) meth.newInstance(mContext, objectList, lifeful);
                //  listView.setOnItemClickListener(this);
                listView.setAdapter(baseAdapter);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

        }
        return page;
    }

    @Override
    public Integer AdapterSetListDatas1(Pagebean<Object> objectPagebean, Integer page, GridView listView, Context mContext, Class<?> cls) {
        try {
            if (objectPagebean.getList().size() > 0) {
                if (objectList == null) {
                    objectList = objectPagebean.getList();
                } else if (objectList != null) {
                    if (page == 1) {
                        objectList.clear();
                    }
                    objectList.addAll(objectPagebean.getList());
                }
                page++;
            }
            if (baseAdapter != null) {
                baseAdapter.notifyDataSetChanged();
            } else {
                Class aClass1 = Class.forName(cls.getName());
                //对应的构造函数
                Constructor meth = aClass1.getConstructor(Context.class, List.class);
                //对应的构造函数的参数
                baseAdapter = (BaseAdapter) meth.newInstance(mContext, objectList);

                listView.setAdapter(baseAdapter);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

        }
        return page;
    }





   /* @Override
    public void AdapterSetListDatas(List<Object> objectList, @LayoutRes int adapter_layout, GridView gridView, Context mContext) {
        try {
            if (baseAdapter != null) {
                baseAdapter.notifyDataSetChanged();
            } else {
                Class aClass = Class.forName(AdapterRealize.ManagerAdapter);
                //对应的构造函数
                Constructor meth1 = aClass.getConstructor(Context.class, List.class, int.class);
                //对应的构造函数的参数
                baseAdapter = (ManagerAdapter) meth1.newInstance(mContext, objectList, adapter_layout);
                gridView.setOnItemClickListener(this);
                gridView.setNumColumns(3);
                gridView.setAdapter(baseAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
*/
//  /*  /**
//     * 用于 服务和个人中心多个Activity
//     */
// @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        ManagerAdapter managerAdapter = null;
//        if (parent.getAdapter() instanceof HeaderViewListAdapter) {
//            HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
//            managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
//
//        } else if (parent.getAdapter() instanceof ManagerAdapter) {
//            managerAdapter = (ManagerAdapter) parent.getAdapter();
//        }
//        List<Object> objectList = managerAdapter.getList();//获取当前adapter里的数据
//        Context context = managerAdapter.getContext();//获取当前的context对象
//        Class aClass = managerAdapter.getCls();//获取跳转的activity类。
//        // Log.i("exp", "aClass==============" + aClass.getName());
//        if (aClass == null) {
//            if (objectList != null && objectList.size() > 0 && objectList.get(position) instanceof ActivityList) {
//                ActivityList activityLists = (ActivityList) objectList.get(position);
//                if (activityLists != null && activityLists.getActivity() != null) {
//                    if (((Class<?>) activityLists.getActivity()).getName().equals("pinyunkeji.hzwl.syhzb.control.activity.user.UserGoodsSourceActivity")) {
//                        // MobclickAgent.onEvent(mContext, "wo_de_huo_yuan");
//                    }
//                    if (((Class<?>) activityLists.getActivity()).getName().equals("pinyunkeji.hzwl.syhzb.control.activity.user.UserCarSourceActivity")) {
//                        //MobclickAgent.onEvent(mContext, "wo_de_che_yuan");
//                    }
//                    aClass = (Class<?>) activityLists.getActivity();
//                }
//            }
//        }
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("data", (Serializable) objectList);
//        bundle.putString("tag", context.getClass().getName());//pinyunkeji.hzwl.syhzb.view.activity.MainActivity
//
//        // IntentManager.getInstance().startNewActivity(context, aClass,objectList, false);
//
//        ActivityUtils.startActivity(bundle, aClass);
//    }
//**/

}


