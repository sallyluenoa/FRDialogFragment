package org.fog_rock.frdialogfragment.extension

internal inline fun <reified ChildT> Any?.downCast(): ChildT? = if (this is ChildT) this else null
