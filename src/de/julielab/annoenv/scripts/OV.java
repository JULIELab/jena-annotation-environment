package de.julielab.annoenv.scripts;



//----------------------------------------------------------------------------
// General-purpose wrapper class used for Output Value passed through 
// function argument.
//
// THIS CLASS IS USED VERY WIDELY THROUGHOUT *ALL* PARTS OF THE CODE.

public
class OV<T>
	{
	public T value;
	public OV( T val0 ) { value = val0; }
	}


