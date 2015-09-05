/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api.energy;

/**
 * Interface to use on blocks/items that can hold Potential Energy (WIP)
 * @author shinoow
 *
 * @since 1.4.5
 */
public interface IEnergyContainer {

	public int getContainedEnergy();

	public int getmaxEnergy();

	public void addEnergy(float energy);

	public void consumeEnergy(float energy);
}
