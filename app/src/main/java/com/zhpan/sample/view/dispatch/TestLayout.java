package com.zhpan.sample.view.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class TestLayout extends ViewGroup {

    public TestLayout(Context context) {
        super(context);
    }

    public TestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onTouchEvent(ev, 1);
//        }
//
//        // If the event targets the accessibility focused view and this is it, start
//        // normal event dispatch. Maybe a descendant is what will handle the click.
//        if (ev.isTargetAccessibilityFocus() && isAccessibilityFocusedViewOrHost()) {
//            ev.setTargetAccessibilityFocus(false);
//        }
//        // 事件是否处理
//        boolean handled = false;
//        if (onFilterTouchEventForSecurity(ev)) {
//            final int action = ev.getAction();
//            final int actionMasked = action & MotionEvent.ACTION_MASK;
//
//            // Handle an initial down.
//            if (actionMasked == MotionEvent.ACTION_DOWN) { // Down事件意味着一个事件序列的开始，因此需要清除TouchTarget,并重置状态
//                // Throw away all previous state when starting a new touch gesture.
//                // The framework may have dropped the up or cancel event for the previous gesture
//                // due to an app switch, ANR, or some other state change.
//                cancelAndClearTouchTargets(ev);
//                resetTouchState();
//            }
//
//            // Check for interception.
//            final boolean intercepted; // 是否拦截事件
//            if (actionMasked == MotionEvent.ACTION_DOWN
//                    || mFirstTouchTarget != null) { // 如果是Down事件，或者已经有了消费事件的子View
//                final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//                if (!disallowIntercept) {
//                    intercepted = onInterceptTouchEvent(ev);
//                    ev.setAction(action); // restore action in case it was changed
//                } else {// 此处是子View禁止了该ViewGroup拦截事件，因此不对事件进行拦截
//                    intercepted = false;
//                }
//            } else { // 此时，不是Down事件，并且子View没有消费事件，则该ViewGroup拦截事件
//                // There are no touch targets and this action is not an initial down
//                // so this view group continues to intercept touches.
//                intercepted = true;
//            }
//
//            // If intercepted, start normal event dispatch. Also if there is already
//            // a view that is handling the gesture, do normal event dispatch.
//            if (intercepted || mFirstTouchTarget != null) {
//                ev.setTargetAccessibilityFocus(false);
//            }
//
//            // Check for cancelation.
//            final boolean canceled = resetCancelNextUpFlag(this)
//                    || actionMasked == MotionEvent.ACTION_CANCEL;
//
//            // Update list of touch targets for pointer down, if needed.
//            final boolean split = (mGroupFlags & FLAG_SPLIT_MOTION_EVENTS) != 0;
//            TouchTarget newTouchTarget = null;
//            boolean alreadyDispatchedToNewTouchTarget = false;
//            if (!canceled && !intercepted) {// 该ViewGroup不拦截事件，应该交由其子View处理事件
//
//                // If the event is targeting accessibility focus we give it to the
//                // view that has accessibility focus and if it does not handle it
//                // we clear the flag and dispatch the event to all children as usual.
//                // We are looking up the accessibility focused host to avoid keeping
//                // state since these events are very rare.
//                View childWithAccessibilityFocus = ev.isTargetAccessibilityFocus()
//                        ? findChildWithAccessibilityFocus() : null;
//
//                if (actionMasked == MotionEvent.ACTION_DOWN
//                        || (split && actionMasked == MotionEvent.ACTION_POINTER_DOWN)
//                        || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                    final int actionIndex = ev.getActionIndex(); // always 0 for down
//                    final int idBitsToAssign = split ? 1 << ev.getPointerId(actionIndex)
//                            : TouchTarget.ALL_POINTER_IDS;
//
//                    // Clean up earlier touch targets for this pointer id in case they
//                    // have become out of sync.
//                    removePointersFromTouchTargets(idBitsToAssign);
//
//                    final int childrenCount = mChildrenCount;
//                    if (newTouchTarget == null && childrenCount != 0) {
//                        final float x = ev.getX(actionIndex);
//                        final float y = ev.getY(actionIndex);
//                        // Find a child that can receive the event.
//                        // Scan children from front to back.
//                        final ArrayList<View> preorderedList = buildTouchDispatchChildList();
//                        final boolean customOrder = preorderedList == null
//                                && isChildrenDrawingOrderEnabled();
//                        final View[] children = mChildren;
//                        for (int i = childrenCount - 1; i >= 0; i--) {
//                            final int childIndex = getAndVerifyPreorderedIndex(
//                                    childrenCount, i, customOrder);
//                            // 获取到能接受该事件的子View
//                            final View child = getAndVerifyPreorderedView(
//                                    preorderedList, children, childIndex);
//
//                            // If there is a view that has accessibility focus we want it
//                            // to get the event first and if not handled we will perform a
//                            // normal dispatch. We may do a double iteration but this is
//                            // safer given the timeframe.
//                            if (childWithAccessibilityFocus != null) {
//                                if (childWithAccessibilityFocus != child) {
//                                    continue;
//                                }
//                                childWithAccessibilityFocus = null;
//                                i = childrenCount - 1;
//                            }
//
//                            if (!canViewReceivePointerEvents(child)
//                                    || !isTransformedTouchPointInView(x, y, child, null)) {
//                                ev.setTargetAccessibilityFocus(false);
//                                continue;
//                            }
//
//                            newTouchTarget = getTouchTarget(child);
//                            if (newTouchTarget != null) {
//                                // Child is already receiving touch within its bounds.
//                                // Give it the new pointer in addition to the ones it is handling.
//                                newTouchTarget.pointerIdBits |= idBitsToAssign;
//                                break;
//                            }
//
//                            resetCancelNextUpFlag(child);
//                            // 子View或者自身消费了事件，注意此处是Down事件
//                            if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
//                                // Child wants to receive touch within its bounds.
//                                mLastTouchDownTime = ev.getDownTime();
//                                if (preorderedList != null) {
//                                    // childIndex points into presorted list, find original index
//                                    for (int j = 0; j < childrenCount; j++) {
//                                        if (children[childIndex] == mChildren[j]) {
//                                            mLastTouchDownIndex = j;
//                                            break;
//                                        }
//                                    }
//                                } else {
//                                    mLastTouchDownIndex = childIndex;
//                                }
//                                mLastTouchDownX = ev.getX();
//                                mLastTouchDownY = ev.getY();
//                                // 存储child作为TouchTarget
//                                newTouchTarget = addTouchTarget(child, idBitsToAssign);
//                                alreadyDispatchedToNewTouchTarget = true;
//                                break;
//                            }
//
//                            // The accessibility focus didn't handle the event, so clear
//                            // the flag and do a normal dispatch to all children.
//                            ev.setTargetAccessibilityFocus(false);
//                        }
//                        if (preorderedList != null) preorderedList.clear();
//                    }
//
//                    if (newTouchTarget == null && mFirstTouchTarget != null) {
//                        // Did not find a child to receive the event.
//                        // Assign the pointer to the least recently added target.
//                        newTouchTarget = mFirstTouchTarget;
//                        while (newTouchTarget.next != null) {
//                            newTouchTarget = newTouchTarget.next;
//                        }
//                        newTouchTarget.pointerIdBits |= idBitsToAssign;
//                    }
//                }
//            }
//
//            // Dispatch to touch targets.
//            if (mFirstTouchTarget == null) {
//                // No touch targets so treat this as an ordinary view.
//                // 没有TargetView，则认为没有子View，把其作为一个普通View，交由自身处理分发事件
//                handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                        TouchTarget.ALL_POINTER_IDS);
//            } else {
//                // Dispatch to touch targets, excluding the new touch target if we already
//                // dispatched to it.  Cancel touch targets if necessary.
//                TouchTarget predecessor = null;
//                TouchTarget target = mFirstTouchTarget;
//                while (target != null) {
//                    final TouchTarget next = target.next;
//                    if (alreadyDispatchedToNewTouchTarget && target == newTouchTarget) {
//                        handled = true;
//                    } else {
//                        final boolean cancelChild = resetCancelNextUpFlag(target.child)
//                                || intercepted;
//                        // 子View消费了事件，那么把handled置为true，并返回，表示已消费事件
//                        if (dispatchTransformedTouchEvent(ev, cancelChild,
//                                target.child, target.pointerIdBits)) {
//                            handled = true;
//                        }
//                        if (cancelChild) {
//                            if (predecessor == null) {
//                                mFirstTouchTarget = next;
//                            } else {
//                                predecessor.next = next;
//                            }
//                            target.recycle();
//                            target = next;
//                            continue;
//                        }
//                    }
//                    predecessor = target;
//                    target = next;
//                }
//            }
//
//            // Update list of touch targets for pointer up or cancel, if needed.
//            if (canceled
//                    || actionMasked == MotionEvent.ACTION_UP
//                    || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                resetTouchState();
//            } else if (split && actionMasked == MotionEvent.ACTION_POINTER_UP) {
//                final int actionIndex = ev.getActionIndex();
//                final int idBitsToRemove = 1 << ev.getPointerId(actionIndex);
//                removePointersFromTouchTargets(idBitsToRemove);
//            }
//        }
//
//        if (!handled && mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onUnhandledEvent(ev, 1);
//        }
//        return handled;
//    }
//
//    private TouchTarget addTouchTarget(@NonNull View child, int pointerIdBits) {
//        final TouchTarget target = TouchTarget.obtain(child, pointerIdBits);
//        target.next = mFirstTouchTarget;
//        mFirstTouchTarget = target;
//        return target;
//    }
//
//    /**
//     * Gets the touch target for specified child view.
//     * Returns null if not found.
//     */
//    private TouchTarget getTouchTarget(@NonNull View child) {
//        for (TouchTarget target = mFirstTouchTarget; target != null; target = target.next) {
//            if (target.child == child) {
//                return target;
//            }
//        }
//        return null;
//    }
//
//    private boolean dispatchTransformedTouchEvent(MotionEvent event, boolean cancel,
//                                                  View child, int desiredPointerIdBits) {
//        final boolean handled;
//
//        // ...省略代码
//
//        // Perform any necessary transformations and dispatch.
//        if (child == null) {// 自View为空，则事件无法再向下分发，调用自身的事件分发
//            handled = super.dispatchTouchEvent(transformedEvent);
//        } else {
//            final float offsetX = mScrollX - child.mLeft;
//            final float offsetY = mScrollY - child.mTop;
//            transformedEvent.offsetLocation(offsetX, offsetY);
//            if (! child.hasIdentityMatrix()) {
//                transformedEvent.transform(child.getInverseMatrix());
//            }
//            // 子View非空的情况则调用子View，继续进行事件分发，并将子View是否处理事件作为返回值。
//            handled = child.dispatchTouchEvent(transformedEvent);
//        }
//
//        // Done.
//        transformedEvent.recycle();
//        return handled;
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}