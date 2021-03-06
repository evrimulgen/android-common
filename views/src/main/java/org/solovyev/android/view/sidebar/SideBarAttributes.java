package org.solovyev.android.view.sidebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import org.solovyev.android.view.R;

import javax.annotation.Nonnull;

final class SideBarAttributes {

	/**
	 * Sliding view ledge size in pixels.
	 * Setting positive value will cause part of the sliding view to be always shown
	 */
	private int slidingViewLedge = 0;

	/**
	 * Identifier of the main view
	 */
	private int mainViewId = View.NO_ID;

	/**
	 * Identifier of the sliding view
	 */
	private int slidingViewId = View.NO_ID;

	private boolean slideMainView = false;

	@Nonnull
	private SideBarSlidingViewPosition slidingViewPosition = SideBarSlidingViewPosition.left;

	@Nonnull
	private SideBarSlidingViewStyle slidingViewStyle = SideBarSlidingViewStyle.push;

	private SideBarAttributes() {
	}

	@Nonnull
	public static SideBarAttributes newAttributes(@Nonnull Context context, @Nonnull AttributeSet attrs) {
		final SideBarAttributes result = new SideBarAttributes();

		final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SideBarLayout);

		result.mainViewId = attributes.getResourceId(R.styleable.SideBarLayout_mainViewId, View.NO_ID);
		result.slidingViewId = attributes.getResourceId(R.styleable.SideBarLayout_slidingViewId, View.NO_ID);
		result.slidingViewLedge = attributes.getDimensionPixelSize(R.styleable.SideBarLayout_slidingViewLedge, 0);
		result.slidingViewPosition = SideBarSlidingViewPosition.getById(attributes.getInt(R.styleable.SideBarLayout_slidingViewPosition, 0));
		result.slidingViewStyle = SideBarSlidingViewStyle.getById(attributes.getInt(R.styleable.SideBarLayout_slidingViewStyle, 0));

		attributes.recycle();

		checkAttributes(result);

		return result;
	}

	private static void checkAttributes(SideBarAttributes result) {
		if (result.mainViewId == View.NO_ID) {
			throw new IllegalArgumentException("Main view id must be set!");
		}

		if (result.slidingViewId == View.NO_ID) {
			throw new IllegalArgumentException("Sliding view id must be set!");
		}
	}

	@Nonnull
	public static SideBarAttributes newAttributes(int mainViewId, int slidingViewId, int slidingViewLedge, SideBarSlidingViewPosition slidingViewPosition) {
		final SideBarAttributes result = new SideBarAttributes();

		result.mainViewId = mainViewId;
		result.slidingViewId = slidingViewId;
		result.slidingViewLedge = slidingViewLedge;
		result.slidingViewPosition = slidingViewPosition;

		checkAttributes(result);

		return result;
	}

	public int getSlidingViewLedge() {
		return slidingViewLedge;
	}

	public int getMainViewId() {
		return mainViewId;
	}

	public int getSlidingViewId() {
		return slidingViewId;
	}

	public boolean isSlideMainView() {
		return slideMainView;
	}

	public SideBarSlidingViewPosition getSlidingViewPosition() {
		return slidingViewPosition;
	}

	public boolean isSlidingViewLedgeExists() {
		return slidingViewLedge > 0;
	}

	public SideBarSlidingViewStyle getSlidingViewStyle() {
		return slidingViewStyle;
	}
}
