package dev.jonium.mason.collections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.core.Alt;
import dev.jonium.mason.core.Control;

import java.util.ArrayList;

/**
 * Container and Jackson support for {@link Alt} objects that are alternatives to the main control
 *
 * @see Control
 * @see Alt
 */
@JsonRootName(Tokens.Controls.ALT)
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonSerialize(contentAs = Alt.class)
@JsonDeserialize(contentAs = Alt.class)
public class Alts extends ArrayList<Alt> {}
