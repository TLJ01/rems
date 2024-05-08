package com.tan.utils;

import com.tan.entity.EntityDoctor;

/**
 * 利用ThreadLocal存储登录后的用户信息
 */
public class UserThreadLocal {
   private static final ThreadLocal<EntityDoctor> LOCAL = new ThreadLocal<>();

   private UserThreadLocal() {}

   public static void put(EntityDoctor user) {
      LOCAL.set(user);
   }
   public static EntityDoctor get() {
      return LOCAL.get();
   }
   public static void remove() {
      LOCAL.remove();
   }
}
