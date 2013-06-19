/*
 * HYPE_Processing
 * http://www.hypeframework.org/ & https://github.com/hype/HYPE_Processing
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */

package hype.extended.layout;

import hype.core.drawable.HDrawable;
import hype.core.layout.HLayout;
import hype.core.util.H;
import hype.core.util.HMath;
import processing.core.PVector;

public class HShapeLayout implements HLayout {
	private HDrawable _target;
	private int _iterationLimit;
	
	public HShapeLayout() {
		_iterationLimit = 1024;
	}
	
	public HShapeLayout iterationLimit(int i) {
		_iterationLimit = i;
		return this;
	}
	
	public int iterationLimit() {
		return _iterationLimit;
	}
	
	public HShapeLayout target(HDrawable h) {
		_target = h;
		return this;
	}
	
	public HDrawable target() {
		return _target;
	}
	
	@Override
	public void applyTo(HDrawable target) {
		PVector pt = getNextPoint();
		if(pt != null) target.loc(pt);
	}
	
	@Override
	public PVector getNextPoint() {
		if(_target == null) return null;
		
		float[] loc = HMath.absLocArr(_target,0,0);
		float x1 = loc[0] - _target.anchorX();
		float y1 = loc[1] - _target.anchorY();
		float x2 = x1 + _target.width();
		float y2 = y1 + _target.height();
		
		for(int i=0; i<_iterationLimit; ++i) {
			float x = H.app().random(x1,x2);
			float y = H.app().random(y1,y2);
			if(_target.contains(x,y))
				return new PVector(x,y);
		}
		return null;
	}
}
