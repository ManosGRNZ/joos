package gr.structuraldesign.analysis;

/**
 * This enum hold all the cases for the load types
 * that will be handled in the Structure class.
 * @author Manos Bairaktaris
 *
 */
public enum LoadType {
	NODE_PX, NODE_PY, NODE_PZ, NODE_MX, NODE_MY, NODE_MZ, 
	
	BEAM_GLOBAL_UNIFORM_PROJECTION_X, BEAM_GLOBAL_UNIFORM_PROJECTION_Y, BEAM_GLOBAL_UNIFORM_PROJECTION_Z,
	BEAM_GLOBAL_UNIFORM_ACTUAL_X, BEAM_GLOBAL_UNIFORM_ACTUAL_Y, BEAM_GLOBAL_UNIFORM_ACTUAL_Z,
	
	BEAM_GLOBAL_VARIABLE_PROJECTION_X, BEAM_GLOBAL_VARIABLE_PROJECTION_Y, BEAM_GLOBAL_VARIABLE_PROJECTION_Z,
	BEAM_GLOBAL_VARIABLE_ACTUAL_X, BEAM_GLOBAL_VARIABLE_ACTUAL_Y, BEAM_GLOBAL_VARIABLE_ACTUAL_Z,
	
	BEAM_GLOBAL_POINT_PX, BEAM_GLOBAL_POINT_PY, BEAM_GLOBAL_POINT_PZ, BEAM_GLOBAL_POINT_MX, BEAM_GLOBAL_POINT_MY, BEAM_GLOBAL_POINT_MZ,
	
	BEAM_LOCAL_UNIFORM_PROJECTION_X, BEAM_LOCAL_UNIFORM_PROJECTION_Y, BEAM_LOCAL_UNIFORM_PROJECTION_Z,
	BEAM_LOCAL_UNIFORM_ACTUAL_X, BEAM_LOCAL_UNIFORM_ACTUAL_Y, BEAM_LOCAL_UNIFORM_ACTUAL_Z,
	
	BEAM_LOCAL_VARIABLE_PROJECTION_X, BEAM_LOCAL_VARIABLE_PROJECTION_Y, BEAM_LOCAL_VARIABLE_PROJECTION_Z,
	BEAM_LOCAL_VARIABLE_ACTUAL_X, BEAM_LOCAL_VARIABLE_ACTUAL_Y, BEAM_LOCAL_VARIABLE_ACTUAL_Z,
	
	BEAM_LOCAL_POINT_N, BEAM_LOCAL_POINT_PY, BEAM_LOCAL_POINT_PZ, BEAM_LOCAL_POINT_T, BEAM_LOCAL_POINT_MY, BEAM_LOCAL_POINT_MZ,
	
	AREA_GLOBAL_UNIFORM_PROJECTION_PX, AREA_GLOBAL_UNIFORM_PROJECTION_PY, AREA_GLOBAL_UNIFORM_PROJECTION_PZ,
	AREA_LOCAL_UNIFORM_PROJECTION_PX, AREA_LOCAL_UNIFORM_PROJECTION_PY, AREA_LOCAL_UNIFORM_PROJECTION_PZ,
	
	AREA_GLOBAL_UNIFORM_ACTUAL_PX, AREA_GLOBAL_UNIFORM_ACTUAL_PY, AREA_GLOBAL_UNIFORM_ACTUAL_PZ,
	AREA_LOCAL_UNIFORM_ACTUAL_PX, AREA_LOCAL_UNIFORM_ACTUAL_PY, AREA_LOCAL_UNIFORM_ACTUAL_PZ,
	
	AREA_GLOBAL_VARIABLE_PROJECTION_PX, AREA_GLOBAL_VARIABLE_PROJECTION_PY, AREA_GLOBAL_VARIABLE_PROJECTION_PZ,
	AREA_LOCAL_VARIABLE_PROJECTION_PX, AREA_LOCAL_VARIABLE_PROJECTION_PY, AREA_LOCAL_VARIABLE_PROJECTION_PZ,
	
	AREA_GLOBAL_VARIABLE_ACTUAL_PX, AREA_GLOBAL_VARIABLE_ACTUAL_PY, AREA_GLOBAL_VARIABLE_ACTUAL_PZ,
	AREA_LOCAL_VARIABLE_ACTUAL_PX, AREA_LOCAL_VARIABLE_ACTUAL_PY, AREA_LOCAL_VARIABLE_ACTUAL_PZ,

	AREA_LINE_GLOBAL_UNIFORM_PROJECTION_PX, AREA_LINE_GLOBAL_UNIFORM_PROJECTION_PY, AREA_LINE_GLOBAL_UNIFORM_PROJECTION_PZ,
	AREA_LINE_LOCAL_UNIFORM_PROJECTION_PX, AREA_LINE_LOCAL_UNIFORM_PROJECTION_PY, AREA_LINE_LOCAL_UNIFORM_PROJECTION_PZ,
	
	AREA_LINE_GLOBAL_UNIFORM_ACTUAL_PX, AREA_LINE_GLOBAL_UNIFORM_ACTUAL_PY, AREA_LINE_GLOBAL_UNIFORM_ACTUAL_PZ,
	AREA_LINE_LOCAL_UNIFORM_ACTUAL_PX, AREA_LINE_LOCAL_UNIFORM_ACTUAL_PY, AREA_LINE_LOCAL_UNIFORM_ACTUAL_PZ,
	
	AREA_LINE_GLOBAL_VARIABLE_PROJECTION_PX, AREA_LINE_GLOBAL_VARIABLE_PROJECTION_PY, AREA_LINE_GLOBAL_VARIABLE_PROJECTION_PZ,
	AREA_LINE_LOCAL_VARIABLE_PROJECTION_PX, AREA_LINE_LOCAL_VARIABLE_PROJECTION_PY, AREA_LINE_LOCAL_VARIABLE_PROJECTION_PZ,
	
	AREA_LINE_GLOBAL_VARIABLE_ACTUAL_PX, AREA_LINE_GLOBAL_VARIABLE_ACTUAL_PY, AREA_LINE_GLOBAL_VARIABLE_ACTUAL_PZ,
	AREA_LINE_LOCAL_VARIABLE_ACTUAL_PX, AREA_LINE_LOCAL_VARIABLE_ACTUAL_PY, AREA_LINE_LOCAL_VARIABLE_ACTUAL_PZ,
	
	AREA_GLOBAL_POINT_PX, AREA_GLOBAL_POINT_PY, AREA_GLOBAL_POINT_PZ, AREA_GLOBAL_POINT_MX, AREA_GLOBAL_POINT_MY, AREA_GLOBAL_POINT_MZ,
	AREA_LOCAL_POINT_N, AREA_LOCAL_POINT_PY, AREA_LOCAL_POINT_PZ, AREA_LOCAL_POINT_T, AREA_LOCAL_POINT_MY, AREA_LOCAL_POINT_MZ;
 	
}